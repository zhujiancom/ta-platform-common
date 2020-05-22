package com.ta.platform.common.service;

import com.ta.platform.common.system.model.SysLogModel;

/**
 * Creator: zhuji
 * Date: 5/20/2020
 * Time: 6:30 PM
 * Description:
 */
public interface ISysBaseService {
    /**
     * 通过 @SysLog 注解的方式会调用此方法
     * @param sysLogModel
     */
    void addLog(SysLogModel sysLogModel);

    /**
     * 日志添加
     *
     * @param LogContent  内容
     * @param logType     日志类型(0:操作日志;1:登录日志;2:定时任务)
     * @param operatetype 操作类型(1:添加;2:修改;3:删除;)
     */
    void addLog(String LogContent, Integer logType, Integer operatetype);
}
