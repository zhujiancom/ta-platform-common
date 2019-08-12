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
 * Description: 用户表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = -5965261838076725570L;
    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 密码
     */
    private String password;

    /**
     * md5密码盐
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 生日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 性别（1：男 2：女）
     */
    @Dict(dictCode = "sex")
    private Integer sex;

    /**
     * 电子邮件
     */
    private String email;

    private String phone;

    /**
     * 机构code
     */
    private String orgCode;

    /**
     * 状态(1：正常  2：冻结 ）
     */
    @Dict(dictCode = "user_status")
    private Integer status;

    /**
     * 删除状态（0，正常，1已删除）
     */
    private String delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 同步工作流引擎1同步0不同步
     */
    private String activitiSync;
}
