package com.ta.platform.common.web;

import com.ey.tax.toolset.core.StrUtil;
import com.ta.platform.common.system.model.LoginUserInfo;
import com.ta.platform.common.tool.ApplicationContextProvider;
import com.ta.platform.modules.system.entity.SysPermissionDataRule;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Creator: zhuji
 * Date: 8/9/2019
 * Time: 10:50 AM
 * Description:
 */
public class WebContext {
    /**
     * 用户所属机构Id
     */
    public static final String SYS_ORG_ID = "sysOrgId";

    /**
     * 所属机构编码
     */
    public static final String SYS_ORG_CODE = "sysOrgCode";

    /**
     * 用户所属多机构编码， 值以逗号（，）分隔
     */
    public static final String SYS_MULTI_ORG_CODE = "sysMultiOrgCode";

    /**
     * 用户所属部门
     */
    public static final String SYS_DEPART_CODE = "sysDepartCode";

    /**
     * 用户所属多部门
     */
    public static final String SYS_MULTI_DEPART_CODE = "sysMultiDepartCode";

    /**
     * 系统用户编码（对应登录用户账号）
     */
    public static final String SYS_USER_CODE = "sysUserCode";

    /**
     * 系统用户Id
     */
    public static final String SYS_USER_ID = "sysUserId";

    /**
     * 登录用户真实姓名
     */
    public static final String SYS_USER_NAME = "sysUserName";

    /**
     * 系统日期"yyyy-MM-dd"
     */
    public static final String SYS_DATE = "sysDate";

    /**
     * 系统时间"yyyy-MM-dd HH:mm"
     */
    public static final String SYS_DATETIME = "sysDateTime";

    public static final String DATA_AUTHOR_RULES = "DATA_AUTHOR_RULES";

    public static final String DATA_AUTHOR_RULE_SQL = "DATA_AUTHOR_RULE_SQL";

    public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";

    /**
     * 链接请求中追加数据权限查询条件
     *
     * @param request
     * @param dataRules
     */
    public static void appendDataRuleSearchCondition(HttpServletRequest request, List<SysPermissionDataRule> dataRules) {
        List<SysPermissionDataRule> menuDataRules = loadPermissionDataRules();
        if (menuDataRules == null) {
            menuDataRules = new ArrayList<>();
        }
        for (SysPermissionDataRule rule : dataRules) {
            menuDataRules.add(rule);
        }
        // 往menuDataRules里面增量存值
        request.setAttribute(DATA_AUTHOR_RULES, menuDataRules);
    }

    /**
     * 链接请求中追加数据权限查询sql
     *
     * @param request
     * @param sql
     */
    public static void appendDataRuleSearchCondition(HttpServletRequest request, String sql) {
        String ruleSql = loadSqlDataRules();
        if (StrUtil.isBlank(ruleSql)) {
            request.setAttribute(DATA_AUTHOR_RULE_SQL, sql);
        }
    }

    /**
     * 获取请求中的数据权限
     *
     * @return
     */
    public static List<SysPermissionDataRule> loadPermissionDataRules() {
        return (List<SysPermissionDataRule>) ApplicationContextProvider.getHttpServletRequest().getAttribute(DATA_AUTHOR_RULES);
    }

    /**
     * 获取请求中的SQL条件
     *
     * @return
     */
    public static String loadSqlDataRules() {
        return (String) ApplicationContextProvider.getHttpServletRequest().getAttribute(DATA_AUTHOR_RULE_SQL);
    }

    /**
     * 设置登录用户信息
     *
     * @param request
     * @param userInfo
     */
    public static void installLoginUserInfo(HttpServletRequest request, LoginUserInfo userInfo) {
        request.setAttribute(LOGIN_USER_INFO, userInfo);
    }

    public static LoginUserInfo loadLoginUserInfo() {
        return (LoginUserInfo) ApplicationContextProvider.getHttpServletRequest().getAttribute(LOGIN_USER_INFO);
    }


}
