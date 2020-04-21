package com.ta.platform.common.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Creator: zhuji
 * Date: 8/6/2019
 * Time: 6:49 PM
 * Description: 字典注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictSupport {
    boolean value() default false;
}
