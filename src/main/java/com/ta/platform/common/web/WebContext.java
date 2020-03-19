package com.ta.platform.common.web;

import com.ey.tax.toolset.core.StrUtil;
import com.ta.platform.common.system.model.SysPermissionDataRuleModel;
import com.ta.platform.common.system.model.SysUserCacheInfo;
import com.ta.platform.common.tool.ApplicationContextProvider;

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

    public static final String DATA_AUTHOR_RULES = "DATA_AUTHOR_RULES";

    public static final String DATA_AUTHOR_RULE_SQL = "DATA_AUTHOR_RULE_SQL";

    public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";

    /**
     * 链接请求中追加数据权限查询条件
     *
     * @param request
     * @param dataRules
     */
    public static void appendDataRuleSearchCondition(HttpServletRequest request, List<SysPermissionDataRuleModel> dataRules) {
        List<SysPermissionDataRuleModel> menuDataRules = loadPermissionDataRules();
        if (menuDataRules == null) {
            menuDataRules = new ArrayList<>();
        }
        for (SysPermissionDataRuleModel rule : dataRules) {
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
    public static List<SysPermissionDataRuleModel> loadPermissionDataRules() {
        return (List<SysPermissionDataRuleModel>) ApplicationContextProvider.getHttpServletRequest().getAttribute(DATA_AUTHOR_RULES);
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
    public static void installLoginUserInfo(HttpServletRequest request, SysUserCacheInfo userInfo) {
        request.setAttribute(LOGIN_USER_INFO, userInfo);
    }

    public static SysUserCacheInfo loadLoginUserInfo() {
        return (SysUserCacheInfo) ApplicationContextProvider.getHttpServletRequest().getAttribute(LOGIN_USER_INFO);
    }


}
