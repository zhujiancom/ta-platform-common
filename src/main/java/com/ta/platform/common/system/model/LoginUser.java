package com.ta.platform.common.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 10:12 AM
 * Description: 登录用户模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 6276828358486005430L;

    /**
     * 登录人id
     */
    private String id;

    /**
     * 登录人账号
     */
    private String username;

    /**
     * 登录人名字
     */
    private String realname;

    /**
     * 登录人组织
     */
    private String orgCode;

    /**
     * 登录人部门
     */
    private String departCode;

    /**
     * 登录人头像
     */
    private String avatar;

    /**
     * 性别（1： 男， 2：女）
     */
    private Integer sex;

    private String email;

    /**
     * 生日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String phone;

    /**
     * 状态(1：正常 2：冻结 ）
     */
    private Integer status;

    /**
     * 是否注销
     */
    private Integer delFlag;

    /**
     * 同步工作流引擎（1：同步， 0：不同步）
     */
    private Integer activitiSync;
}
