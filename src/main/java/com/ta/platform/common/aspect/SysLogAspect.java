package com.ta.platform.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.ey.tax.toolset.core.TimeLoggerUtil;
import com.ey.tax.toolset.http.HttpUtil;
import com.ta.platform.common.aspect.annotation.SysLog;
import com.ta.platform.common.factory.SysLogFactory;
import com.ta.platform.common.system.model.LoginUser;
import com.ta.platform.common.system.model.SysLogModel;
import com.ta.platform.common.tool.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Date: 8/7/2019
 * Time: 11:07 AM
 * Description: 系统日志处理切面
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogFactory sysLogFactory;

    private final TimeLoggerUtil timeLoggerUtil = TimeLoggerUtil.getInstance();

    @Pointcut("@annotation(com.ta.platform.common.aspect.annotation.SysLog)")
    public void logPointCut(){}

    @Around("logPointCut()")
    public Object log(ProceedingJoinPoint point) throws Throwable{
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long costTime = System.currentTimeMillis() - beginTime;

        createSysLog(point, costTime);
        return result;
    }

    private void createSysLog(ProceedingJoinPoint point, long costTime){
        try {
            timeLoggerUtil.start("PrintOperationLog<start>");
            log.debug("------- {}打印日志[开始]-----------", point.getSignature().getName());
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();

            //获取操作
            SysLog sysLog = method.getAnnotation(SysLog.class);
            SysLogModel.SysLogModelBuilder logModelBuilder = SysLogModel.builder();
            if(sysLog != null){
                logModelBuilder.logMsg(sysLog.value());
                logModelBuilder.logLevel(sysLog.logLevel());
                logModelBuilder.logType(sysLog.logType());
            }
            //获取请求的类名
            String className = point.getTarget().getClass().getSimpleName();
            String methodName = method.getName();
            logModelBuilder.method(className+"."+methodName+"()");

            //获取请求参数
            Object[] args = point.getArgs();
            try{
                String params = JSONObject.toJSONString(args);
                logModelBuilder.requestParam(params);
            }catch (Exception e){}

            //获取request
            HttpServletRequest request = ApplicationContextProvider.getHttpServletRequest();
            //设置ip地址
            logModelBuilder.ip(HttpUtil.getClientIP(request));

            //获取登录用户信息
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if(sysUser != null){
                logModelBuilder.userId(sysUser.getId());
                logModelBuilder.username(sysUser.getRealname());
                logModelBuilder.loginAccount(sysUser.getUsername());
            }
            logModelBuilder.costTime(costTime);
            logModelBuilder.operateTime(new Date());

            /**
             * 远程调用核心服务保存日志
             */
            sysLogFactory.createSysLog(logModelBuilder.build());

            log.debug("------- {}打印日志[结束]-----------", point.getSignature().getName());
        } finally {
            timeLoggerUtil.stop();
        }
    }
}
