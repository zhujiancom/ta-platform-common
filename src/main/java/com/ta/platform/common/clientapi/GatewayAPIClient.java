package com.ta.platform.common.clientapi;

import com.ta.platform.common.system.model.SysLogModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
