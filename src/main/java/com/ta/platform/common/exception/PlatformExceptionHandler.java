package com.ta.platform.common.exception;

import com.ta.platform.common.api.vo.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Date: 8/7/2019
 * Time: 2:38 PM
 * Description: 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class PlatformExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler({PlatformException.class,Exception.class})
    public GlobalResponse<?> handleRRException(PlatformException e){
        log.error(e.getMessage(), e);
        return GlobalResponse.error(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public GlobalResponse<?> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return GlobalResponse.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public GlobalResponse<?> handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return GlobalResponse.error("数据库中已存在该记录");
    }

    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public GlobalResponse<?> handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(), e);
        return GlobalResponse.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public GlobalResponse<?> HttpRequestMethodNotSupportedException(Exception e){
        log.error(e.getMessage(), e);
        return GlobalResponse.error("没有权限，请联系管理员授权");
    }
}
