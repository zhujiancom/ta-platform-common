package com.ta.platform.common.tool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ey.tax.toolset.core.ClassUtil;
import com.ey.tax.toolset.core.ReflectUtil;
import com.ey.tax.toolset.core.StrUtil;
import com.ey.tax.toolset.core.TypeUtil;
import com.ey.tax.toolset.core.collection.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.platform.common.aspect.annotation.Dict;
import com.ta.platform.common.aspect.annotation.DictSupport;
import com.ta.platform.common.modules.system.service.ISysDictService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Creator: zhuji
 * Date: 4/20/2020
 * Time: 2:37 PM
 * Description:
 */
@Slf4j
public class DictHelper {
    public static JSONObject parseDictField(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{}";
        try {
            //解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("json解析失败" + e.getMessage(), e);
        }
        JSONObject item = JSONObject.parseObject(json);
        ISysDictService dictService = ApplicationContextProvider.getBean(ISysDictService.class);
        for (Field field : ReflectUtil.getFields(object.getClass())) {
            if (field.getAnnotation(Dict.class) != null) {
                String code = field.getAnnotation(Dict.class).dictCode();
                String text = field.getAnnotation(Dict.class).dictText();
                String table = field.getAnnotation(Dict.class).dictTable();
                String key = String.valueOf(item.get(field.getName()));
                String textValue = null;
                log.info(" 字典 key : " + key);
                if (!StrUtil.isEmpty(table)) {
                    textValue = dictService.queryTableDictTextByKey(table, text, code, key);
                } else {
                    textValue = dictService.queryDictTextByKey(code, key);
                }
                log.info(" 字典Val : " + textValue);
                log.info(" __翻译字典字段__ " + field.getName() + "_dictText： " + textValue);
                item.put(field.getName() + "_dictText", textValue);
            }
            try {
                //date类型默认转换string格式化日期
                if (field.getType().getName().equals("java.util.Date") && field.getAnnotation(JsonFormat.class) == null && item.get(field.getName()) != null) {
                    SimpleDateFormat aDate = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                    item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
                } else if (dictSupport(field.getType())) {
                    item.put(field.getName(), parseDictField(ReflectUtil.getFieldValue(object, field)));
                } else if (TypeUtil.getTypeArgument(field.getGenericType()) != null && dictSupport(Class.forName(TypeUtil.getTypeArgument(field.getGenericType()).getTypeName()))) {
                    Object obj = ReflectUtil.getFieldValue(object, field);
                    if(obj != null && Collection.class.isAssignableFrom(obj.getClass())){
                        JSONArray jsonArray = new JSONArray();
                        ((Collection)obj).stream().forEach(o->{
                            JSONObject jso = parseDictField(o);
                            jsonArray.add(jso);
                        });
                        item.put(field.getName(), jsonArray);
                    }
                }
            } catch (ClassNotFoundException e) {
                log.warn("字典项值转换出错！", e);
            }
        }
        return item;
    }

    public static boolean dictSupport(Class clazz) {
        DictSupport dictSupport = (DictSupport) clazz.getAnnotation(DictSupport.class);
        if (dictSupport != null && dictSupport.value()) {
            return true;
        }
        return false;
    }
}
