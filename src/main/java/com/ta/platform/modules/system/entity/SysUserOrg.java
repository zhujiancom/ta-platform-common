package com.ta.platform.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: 用户机构表
 */
@Data
@TableName("t_sys_user_org")
public class SysUserOrg implements Serializable {
    /**主键id*/
    @TableId(type = IdType.UUID)
    private String id;
    /**用户id*/
    private String userId;
    /**机构id*/
    private String orgId;

    public SysUserOrg(String id, String userId, String orgId) {
        super();
        this.id = id;
        this.userId = userId;
        this.orgId = orgId;
    }
}
