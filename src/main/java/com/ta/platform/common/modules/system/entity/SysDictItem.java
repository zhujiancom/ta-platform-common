package com.ta.platform.common.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ta.platform.common.aspect.annotation.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 2:50 PM
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDictItem implements Serializable {
    private static final long serialVersionUID = 7660779193459886006L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 字典id
     */
    private String dictId;

    /**
     * 字典项文本
     */
    private String itemText;

    /**
     * 字典项值
     */
    private String itemValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sortOrder;


    /**
     * 状态（1启用 0不启用）
     */
    @Dict(dictCode = "dict_item_status")
    private Integer status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
}
