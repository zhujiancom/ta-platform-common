package com.ta.platform.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ta.platform.common.aspect.annotation.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 组织机构表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_org")
public class SysOrg implements Serializable {
    /**ID*/
    @TableId(type = IdType.UUID)
    private String id;
    /**父部门ID*/
    private String parentId;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构级别
     */
    private String levelCode;

    /**
     * 排序
     */
    private Integer orgOrder;

    /**状态（1启用，0不启用）*/
    @Dict(dictCode = "depart_status")
    private String status;

    /**删除状态（0，正常，1已删除）*/
    @Dict(dictCode = "del_flag")
    private String delFlag;

    /**创建人*/
    private String createBy;
    /**创建日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**更新人*/
    private String updateBy;
    /**更新日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
