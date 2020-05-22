package com.ta.platform.common.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ta.platform.common.aspect.annotation.Dict;
import com.ta.platform.common.constant.LogLevel;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Date: 8/7/2019
 * Time: 11:08 AM
 * Description:
 */
@Data
@Builder
public class SysLogModel implements Serializable {
    private static final long serialVersionUID = 3452723888136358209L;

    private String id;

    /**
     * 日志内容
     */
    private String logMsg;

    /**
     * 操作时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /**
     * 耗时
     */
    private Long costTime;

    /**
     * 访问者ip
     */
    private String ip;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求路径
     */
    private String requestUrl;

    /**
     * 日志级别
     */
    private LogLevel logLevel;

    /**
     * 日志类型
     */
    @Dict(dictCode = "log_type")
    private int logType;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 操作人名称
     */
    private String username;

    /**
     * 操作人账户
     */
    private String userId;

    /**
     * 操作人登录账号
     */
    private String loginAccount;

    /**
     * 操作类型: 查询， 新建， 删除， 导入， 导出
     */
    @Dict(dictCode = "operate_type")
    private Integer operateType;
}
