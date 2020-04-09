package com.ta.platform.common.exception;

import com.ta.platform.common.api.vo.Result;
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
    public Result<?> handleRRException(PlatformException e){
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<?> handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public Result<?> handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(), e);
        return Result.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> HttpRequestMethodNotSupportedException(Exception e){
        log.error(e.getMessage(), e);
        return Result.error("没有权限，请联系管理员授权");
    }
}
