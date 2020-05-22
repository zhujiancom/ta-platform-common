package com.ta.platform.common.service.impl;

import com.ey.tax.toolset.http.HttpUtil;
import com.ta.platform.common.convert.SysLogConvert;
import com.ta.platform.common.module.entity.SysLog;
import com.ta.platform.common.module.mapper.SysLogMapper;
import com.ta.platform.common.service.ISysBaseService;
import com.ta.platform.common.system.model.SysLogModel;
import com.ta.platform.common.tool.ApplicationContextProvider;
import com.ta.platform.common.vo.LoginUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Creator: zhuji
 * Date: 5/20/2020
 * Time: 6:31 PM
 * Description:
 */
@Slf4j
@Service
public class SysBaseServiceImpl implements ISysBaseService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void addLog(SysLogModel sysLogModel) {
        SysLog sysLog = SysLogConvert.INSTANCE.sysLogModelToSysLogEntity(sysLogModel);
        //保存系统日志
        sysLogMapper.insert(sysLog);
    }

    @Override
    public void addLog(String LogContent, Integer logType, Integer operatetype) {
        SysLog sysLog = new SysLog();
        //注解上的描述,操作日志内容
        sysLog.setLogContent(LogContent);
        sysLog.setLogType(logType);
        sysLog.setOperateType(operatetype);

        //请求的方法名
        //请求的参数

        try {
            //获取request
            HttpServletRequest request = ApplicationContextProvider.getHttpServletRequest();
            //设置IP地址
            sysLog.setIp(HttpUtil.getClientIP(request));
        } catch (Exception e) {
            sysLog.setIp("127.0.0.1");
        }

        //获取登录用户信息
        LoginUserVo sysUser = (LoginUserVo) SecurityUtils.getSubject().getPrincipal();
        if(sysUser!=null){
            sysLog.setUserid(sysUser.getUsername());
            sysLog.setUsername(sysUser.getRealname());

        }
        sysLog.setCreateTime(new Date());
        //保存系统日志
        sysLogMapper.insert(sysLog);
    }


}
