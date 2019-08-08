package com.ta.platform.common.clientapi;

import feign.hystrix.FallbackFactory;

/**
 * Creator: zhuji
 * Date: 8/6/2019
 * Time: 6:15 PM
 * Description:
 */
public class GatewayHystrixFallbackFactory implements FallbackFactory<GatewayAPIClient> {
    @Override
    public GatewayAPIClient create(Throwable throwable) {
        return null;
    }
}
