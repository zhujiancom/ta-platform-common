package com.ta.platform.common.web.core.filter;

import com.ey.tax.toolset.http.HttpUtil;
import com.ta.platform.common.web.core.bean.RequestDetail;
import com.ta.platform.common.web.core.util.RequestDetailThreadLocal;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Creator: zhuji
 * Date: 5/19/2020
 * Time: 4:16 PM
 * Description: 请求详情信息Filter
 */
@Slf4j
public class RequestDetailFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("------------------- RequestDetailFilter init-------------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 设置请求详情信息
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 请求IP
        String ip = HttpUtil.getClientIP(httpServletRequest);
        // 请求路径
        String path = httpServletRequest.getRequestURI();
        RequestDetail requestDetail = new RequestDetail()
                .setIp(ip)
                .setPath(path);
        // 设置请求详情信息
        RequestDetailThreadLocal.setRequestDetail(requestDetail);
        filterChain.doFilter(servletRequest, servletResponse);
        // 释放
        RequestDetailThreadLocal.remove();
    }

    @Override
    public void destroy() {
        log.info("RequestDetailFilter destroy");
    }
}
