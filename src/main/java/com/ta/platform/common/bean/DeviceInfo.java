package com.ta.platform.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Creator: zhuji
 * Date: 5/19/2020
 * Time: 6:43 PM
 * Description: 设备信息
 */
@Data
public class DeviceInfo implements Serializable {
    private static final long serialVersionUID = 7109168825974955703L;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备型号
     */
    private String model;
}
