package com.ta.platform.common.web.core.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Creator: zhuji
 * Date: 5/19/2020
 * Time: 4:19 PM
 * Description: Filter请求详情信息
 */
@Data
@Accessors(chain = true)
public class RequestDetail  implements Serializable {

    private static final long serialVersionUID = -124400490440470413L;

    /**
     * 请求ip地址
     */
    private String ip;

    /**
     * 请求路径
     */
    private String path;
}
