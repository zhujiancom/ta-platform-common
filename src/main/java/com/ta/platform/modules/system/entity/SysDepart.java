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
 * <p>
 * 部门表
 * <p>
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_depart")
public class SysDepart implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**ID*/
	@TableId(type = IdType.UUID)
	private String id;
	/**父部门ID*/
	private String parentId;
	/**机构/部门名称*/
	private String departName;
	/**英文名*/
	private String departNameEn;
	/**缩写*/
	private String departNameAbbr;
	/**排序*/
	private Integer departOrder;
	/**描述*/
	private Object description;
	/**机构Id*/
	private String orgId;
	/**手机号*/
	private String mobile;
	/**传真*/
	private String fax;
	/**地址*/
	private String address;
	/**备注*/
	private String memo;
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
