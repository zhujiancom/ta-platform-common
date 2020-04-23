package com.ta.platform.common.system.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 10:22 AM
 * Description: 字典项模型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DictModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典项值
     */
    private String value;

    /**
     * 字典项文本
     */
    private String text;

    /**
     * 字典类型， 前端select需要区分整型还是文本， 否则无法显示正确的文本
     */
    private Integer type;

    public String getTitle() {
        return this.text;
    }
}
