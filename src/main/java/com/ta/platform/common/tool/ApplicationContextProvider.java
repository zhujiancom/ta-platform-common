package com.ta.platform.common.tool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 11:09 AM
 * Description:
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static final Log logger = LogFactory.getLog(ApplicationContextProvider.class);

    private static ApplicationContext context;

    private ApplicationContextProvider(){}

    public static ApplicationContext getApplicationContext(){
        return context;
    }

    public static <T> T getBean(String name,Class<T> tClass){
        if(context != null){
            return context.getBean(name,tClass);
        }
        return null;
    }

    public static <T> T getBean(Class<T> tClass){
        return getApplicationContext().getBean(tClass);
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        logger.debug("========ApplicationContext config success ,在普通类可以通过调用ApplicationContextProvider.getApplicationContext()获取applicationContext对象,ApplicationContext="+ApplicationContextProvider.getApplicationContext()+"========");
    }

    /**
     * 获取HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getDomain(){
        HttpServletRequest request = getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    public static String getOrigin(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }
}
