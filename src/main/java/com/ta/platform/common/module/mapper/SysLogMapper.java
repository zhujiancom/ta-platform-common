package com.ta.platform.common.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ta.platform.common.module.entity.SysLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Creator: zhuji
 * Date: 5/20/2020
 * Time: 6:32 PM
 * Description:
 */
@Component
public interface SysLogMapper extends BaseMapper<SysLog> {
    /**
     * @功能：清空所有日志记录
     */
    void removeAll();

    /**
     * 获取系统总访问次数
     *
     * @return Long
     */
    Long findTotalVisitCount();

    //update-begin--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数
    /**
     * 获取系统今日访问次数
     *
     * @return Long
     */
    Long findTodayVisitCount(@Param("dayStart") Date dayStart, @Param("dayEnd") Date dayEnd);

    /**
     * 获取系统今日访问 IP数
     *
     * @return Long
     */
    Long findTodayIp(@Param("dayStart") Date dayStart, @Param("dayEnd") Date dayEnd);
    //update-end--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数

    /**
     *   首页：根据时间统计访问数量/ip数量
     * @param dayStart
     * @param dayEnd
     * @return
     */
    List<Map<String,Object>> findVisitCount(@Param("dayStart") Date dayStart, @Param("dayEnd") Date dayEnd, @Param("dbType") String dbType);
}
