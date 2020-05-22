package com.ta.platform.common.aspect.annotation;

import com.ta.platform.common.constant.CommonConstant;
import com.ta.platform.common.constant.LogLevel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date: 8/7/2019
 * Time: 11:04 AM
 * Description: 系统日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented // 生成文档
public @interface SysLog {
    /**
     * 日志内容
     * @return
     */
    String value() default "";

    /**
     * 系统日志类型
     * @return 0：定时任务日志； 1：登录日志； 2：操作日志
     */
    int logType() default CommonConstant.LOG_TYPE_2;

    /**
     * 日志级别
     * @return
     */
    LogLevel logLevel() default LogLevel.INFO;

    int operateType() default 0;
}
