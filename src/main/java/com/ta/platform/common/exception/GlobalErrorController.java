package com.ta.platform.common.exception;

import com.ta.platform.common.api.ApiCode;
import com.ta.platform.common.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Creator: zhuji
 * Date: 5/19/2020
 * Time: 5:35 PM
 * Description: 全局Error/404处理
 */

@ApiIgnore
@RestController
@Slf4j
public class GlobalErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping(ERROR_PATH)
    public Result<?> handleError(HttpServletRequest request, HttpServletResponse response){
        int status = response.getStatus();
        switch (status){
            case HttpServletResponse.SC_UNAUTHORIZED:
                return Result.error(ApiCode.UNAUTHORIZED);
            case HttpServletResponse.SC_FORBIDDEN:
                return Result.error(ApiCode.NOT_PERMISSION);
            case HttpServletResponse.SC_NOT_FOUND:
                return Result.error(ApiCode.NOT_FOUND);
            default:
                break;
        }
        return Result.error(ApiCode.FAIL);
    }
}
