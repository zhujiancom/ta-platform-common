package com.ta.platform.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * <p>
 * 登录用户模型
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginUserVo implements Serializable{

    private static final long serialVersionUID = -3819267946125678512L;
    /**
     * 登录人id
     */
    @ApiModelProperty("主键")
    private String id;

    /**
     * 登录人账号
     */
    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    /**
     * 登录人名字
     */
    @ApiModelProperty("用户名称")
    private String realname;

    /**
     * 当前登录部门code
     */
    @ApiModelProperty("当前登录部门")
    private String orgCode;
    /**
     * 头像
     */
	@ApiModelProperty("头像")
    private String avatar;

    /**
     * 生日
     */
	@ApiModelProperty("生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 性别（1：男 2：女）
     */
	@ApiModelProperty("性别， 1：男， 2：女")
    private Integer sex;

    /**
     * 电子邮件
     */
	@ApiModelProperty("电子邮件")
    private String email;

    /**
     * 电话
     */
	@ApiModelProperty("电话")
    private String phone;

    /**
     * 状态(1：正常 2：冻结 ）
     */
	@ApiModelProperty("账户状态， 1：正常， 2：冻结")
    private Integer status;

	@ApiModelProperty("删除状态， 0：未删除， 1： 已删除")
    private String delFlag;

    /**
     * 同步工作流引擎1同步0不同步
     */
	@ApiModelProperty("是否同步用户到工作流， 1：同步， 0：不同步")
    private String activitiSync;

    /**
     * 创建时间
     */
	@ApiModelProperty("创建时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 身份（1 普通员工 2 上级）
     */
	@ApiModelProperty("用户身份， 1：普通员工， 2：上级")
    private Integer identity;

    /**
     * 管理部门ids
     */
	@ApiModelProperty("用户所管理的部门")
    private String departIds;

    @ApiModelProperty("角色列表")
	private Set<String> roleCodes;

    @ApiModelProperty("权限编码列表")
    private Set<String> permissionCodes;
}
