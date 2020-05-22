package com.ta.platform.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Creator: zhuji
 * Date: 5/19/2020
 * Time: 6:41 PM
 * Description: 客户端信息
 */
@Data
public class ClientInfo implements Serializable {
    private static final long serialVersionUID = -1105997807511366488L;

    /**
     * ip
     */
    private String ip;

    /**
     * ip对应的地址
     */
    private String address;

    /**
     * 浏览器名称
     */
    private String browserName;

    /**
     * 浏览器版本
     */
    private String browserVersion;

    /**
     * 浏览器引擎名称
     */
    private String engineName;

    /**
     * 浏览器引擎版本
     */
    private String engineVersion;

    /**
     * 系统名称
     */
    private String osName;

    /**
     * 平台名称
     */
    private String platformName;

    /**
     * 是否是手机
     */
    private boolean mobile;

    /**
     * 移动端设备型号
     */
    private String deviceName;

    /**
     * 移动端设备型号
     */
    private String deviceModel;
}
