package com.ta.platform.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;
    
    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

	public SysUserRole() {
	}

	public SysUserRole(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

    

}
