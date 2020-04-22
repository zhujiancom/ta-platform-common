package com.ta.platform.common.system.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 10:22 AM
 * Description: 字典项模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DictModel implements Serializable {

    private static final long serialVersionUID = 1L;

    public DictModel() {
    }

    public DictModel(String value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 字典项值
     */
    private String value;

    /**
     * 字典项文本
     */
    private String text;

    public String getTitle() {
        return this.text;
    }
}
