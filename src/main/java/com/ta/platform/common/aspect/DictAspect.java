package com.ta.platform.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ey.tax.toolset.core.ReflectUtil;
import com.ey.tax.toolset.core.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.platform.common.api.vo.Result;
import com.ta.platform.common.aspect.annotation.Dict;
import com.ta.platform.common.modules.system.service.ISysDictService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 10:39 AM
 * Description: 字典切面处理器
 */
@Aspect
@Component
@Slf4j
public class DictAspect {

    @Autowired
    private ISysDictService dictService;

    @Pointcut("execution(public * com.ta.platform..*.*Controller.*(..))")
    public void executionService(){}

    @Around("executionService()")
    public Object transfer(ProceedingJoinPoint point) throws Throwable{
        long time1=System.currentTimeMillis();
        Object result = point.proceed();
        long time2=System.currentTimeMillis();
        log.debug("获取JSON数据 耗时："+(time2-time1)+"ms");
        long start=System.currentTimeMillis();
        this.parseDictText(result);
        long end=System.currentTimeMillis();
        log.debug("解析注入JSON数据  耗时"+(end-start)+"ms");
        return result;
    }

    private void parseDictText(Object result){
        if(result instanceof Result){
            if(((Result) result).getResult() instanceof IPage){
                List<JSONObject> items = new ArrayList<>();
                for(Object record : ((IPage) ((Result) result).getResult()).getRecords()){
                    ObjectMapper mapper = new ObjectMapper();
                    String json = "{}";
                    try {
                        //解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
                        json = mapper.writeValueAsString(record);
                    } catch (JsonProcessingException e) {
                        log.error("json解析失败"+e.getMessage(),e);
                    }
                    JSONObject item = JSONObject.parseObject(json);
                    for (Field field : ReflectUtil.getFields(record.getClass())) {
                        if (field.getAnnotation(Dict.class) != null) {
                            String code = field.getAnnotation(Dict.class).dictCode();
                            String text = field.getAnnotation(Dict.class).dictText();
                            String table = field.getAnnotation(Dict.class).dictTable();
                            String key = String.valueOf(item.get(field.getName()));
                            String textValue=null;
                            log.info(" 字典 key : "+ key);
                            if(StrUtil.isEmpty(table)){
                                textValue = dictService.queryTableDictTextByKey(table,text,code,key);
                            }else {
                                textValue = dictService.queryDictTextByKey(code, key);
                            }
                            log.info(" 字典Val : "+ textValue);
                            log.info(" __翻译字典字段__ "+field.getName() + "_dictText： "+ textValue);
                            item.put(field.getName() + "_dictText", textValue);
                        }
                        //date类型默认转换string格式化日期
                        if (field.getType().getName().equals("java.util.Date")&&field.getAnnotation(JsonFormat.class)==null&&item.get(field.getName())!=null){
                            SimpleDateFormat aDate=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                            item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
                        }
                    }
                    items.add(item);
                }
                ((IPage) ((Result) result).getResult()).setRecords(items);
            }
        }
    }
}
