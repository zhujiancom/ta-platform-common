package com.ta.platform.common.factory;

import com.ta.platform.common.clientapi.GatewayAPIClient;
import com.ta.platform.common.system.model.SysLogModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 11:10 AM
 * Description: 远程调用核心服务接口创建日志记录
 */
@Slf4j
@Component
public class SysLogFactory {
    @Autowired
    private GatewayAPIClient gatewayAPIClient;

    public void addLog(SysLogModel model){
        gatewayAPIClient.createSysLog(model);
    }
}
