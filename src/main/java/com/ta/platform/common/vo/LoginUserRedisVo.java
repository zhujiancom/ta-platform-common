package com.ta.platform.common.vo;

import com.ta.platform.common.bean.ClientInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Creator: zhuji
 * Date: 5/19/2020
 * Time: 6:44 PM
 * Description: 登录用户Redis对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class LoginUserRedisVo extends LoginUserVo {
    private static final long serialVersionUID = 610608154120102996L;

    /**
     * 登录ip
     */
    private ClientInfo clientInfo;
}
