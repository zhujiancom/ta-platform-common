package com.ta.platform.common.clientapi;

import com.ta.platform.common.api.vo.Result;
import com.ta.platform.common.constant.RequestConstant;
import com.ta.platform.common.system.model.SysLogModel;
import com.ta.platform.common.vo.LoginUserRedisVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Creator: zhuji
 * Date: 8/6/2019
 * Time: 6:00 PM
 * Description:
 */
@FeignClient(value = "gateway", fallbackFactory = GatewayHystrixFallbackFactory.class)
@Component(value = "gatewayAPIClient")
public interface GatewayAPIClient {
    @RequestMapping(value = "/core/api/syslog/create", method = RequestMethod.POST)
    void createSysLog(@RequestBody SysLogModel sysLogModel);

    @RequestMapping(value = "/core/authc/sys/user/all-list", method = RequestMethod.POST)
    Result<Object> getAllSysUser();

    @RequestMapping(value = "/authc/api/auth/user", method = RequestMethod.GET, produces = {"application/*"}, consumes = {"text/plain", "application/*"})
    Result<LoginUserRedisVo> getLoginUser(@RequestParam(value = RequestConstant.X_ACCESS_TOKEN) String token);
}
