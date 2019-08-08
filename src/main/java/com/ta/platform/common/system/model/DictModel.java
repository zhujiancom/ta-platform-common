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

    private static final long serialVersionUID = 7104954756419382759L;

    /**
     * 字典项值
     */
    private String value;

    /**
     * 字典项文本
     */
    private String text;

    /**
     * 前端展示用
     * @return
     */
    public String getTitle(){
        return this.text;
    }
}
