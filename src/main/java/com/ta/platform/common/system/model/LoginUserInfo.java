package com.ta.platform.common.system.model;

import com.ey.tax.toolset.core.date.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Creator: zhuji
 * Date: 8/9/2019
 * Time: 11:11 AM
 * Description: 登录用户信息缓存
 */
@Data
public class LoginUserInfo implements Serializable {
    private String userId;

    private String username;

    private String userCode;

    private String orgCode;

    private List<String> multiOrgCode;

    private String orgId;

    private String orgName;

    private String departCode;

    private String departName;

    private List<String> multiDepartCode;

    public String getSysDate(){
        return DateUtil.formatDate(new Date());
    }

    public String getSysDateTime(){
        return DateUtil.now();
    }
}
