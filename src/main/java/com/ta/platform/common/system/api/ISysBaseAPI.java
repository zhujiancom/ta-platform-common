package com.ta.platform.common.system.api;

import com.ta.platform.common.system.model.DictModel;
import com.ta.platform.common.system.model.LoginUser;

import java.sql.SQLException;
import java.util.List;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 2:16 PM
 * Description: 底层共通业务API，提供其他独立模块调用
 */
public interface ISysBaseAPI {
    /**
     * 根据用户账号查询登录用户信息
     * @param username
     * @return
     */
    LoginUser getUserByName(String username);

    /**
     * 通过用户账号查询角色集合
     * @param username
     * @return
     */
    List<String> getRolesByUsername(String username);

    /**
     * 获取当前数据库类型
     * @return
     * @throws Exception
     */
    String getDatabaseType() throws SQLException;

    /**
     * 获取数据字典
     * @param code
     * @return
     */
    List<DictModel> queryDictItemsByCode(String code);

    /**
     * 获取表数据字典
     * @param table
     * @param text
     * @param code
     * @return
     */
    List<DictModel> queryTableDictItemsByCode(String table, String text, String code);

    /**
     * 查询所有部门 作为字典信息 id -->value,departName -->text
     * @return
     */
    List<DictModel> queryAllDepartBackDictModel();

    /**
     * 发送系统消息
     * @param fromUser 发送人(用户登录账户)
     * @param toUser  发送给(用户登录账户)
     * @param title  消息主题
     * @param msgContent  消息内容
     */
    void sendSysAnnouncement(String fromUser,String toUser,String title, String msgContent);
}
