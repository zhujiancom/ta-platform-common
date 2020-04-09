package com.ta.platform.common.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ey.tax.toolset.core.StrUtil;
import com.ta.platform.common.handler.IFillRuleHandler;
import com.ta.platform.common.modules.system.service.ISysValueFillRuleService;
import lombok.extern.slf4j.Slf4j;


/**
 * 规则值自动生成工具类
 *
 */
@Slf4j
public class FillRuleUtil {

    /**
     * @param ruleCode ruleCode
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object executeRule(String ruleCode, JSONObject formData) {
        if (!StrUtil.isEmpty(ruleCode)) {
            try {
                // 获取 Service
                ServiceImpl impl = (ServiceImpl) ApplicationContextProvider.getBean(ISysValueFillRuleService.class);
                // 根据 ruleCode 查询出实体
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("rule_code", ruleCode);
                JSONObject entity = JSON.parseObject(JSON.toJSONString(impl.getOne(queryWrapper)));
                if (entity == null) {
                    log.warn("填值规则：" + ruleCode + " 不存在");
                    return null;
                }
                // 获取必要的参数
                String ruleClass = entity.getString("ruleClass");
                JSONObject params = entity.getJSONObject("ruleParams");
                if (params == null) {
                    params = new JSONObject();
                }
                if (formData == null) {
                    formData = new JSONObject();
                }
                // 通过反射执行配置的类里的方法
                IFillRuleHandler ruleHandler = (IFillRuleHandler) Class.forName(ruleClass).newInstance();
                return ruleHandler.execute(params, formData);
            } catch (Exception e) {
                log.warn(e.getMessage(),e);
            }
        }
        return null;
    }
}
