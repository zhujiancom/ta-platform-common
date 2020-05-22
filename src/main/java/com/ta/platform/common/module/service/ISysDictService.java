package com.ta.platform.common.module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ta.platform.common.module.entity.SysDict;
import com.ta.platform.common.system.model.DictModel;
import com.ta.platform.common.module.entity.SysDictItem;

import java.util.List;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 12:12 PM
 * Description:
 */
public interface ISysDictService extends IService<SysDict> {
    List<DictModel> queryDictItemsByCode(String code);

    List<DictModel> queryTableDictItemsByCode(String table, String text, String code);

    String queryDictTextByKey(String code, String key);

    String queryTableDictTextByKey(String table, String text, String code, String key);

    /**
     * 根据字典类型删除关联表中其对应的数据
     *
     * @param sysDict
     * @return
     */
    boolean deleteByDictId(SysDict sysDict);

    /**
     * 添加一对多
     * @param sysDict
     * @param sysDictItemList
     */
    void saveMain(SysDict sysDict, List<SysDictItem> sysDictItemList);

    /**
     * 查询所有部门 作为字典信息 id -->value,departName -->text
     * @return
     */
    List<DictModel> queryAllDepartBackDictModel();

    /**
     * 查询所有用户  作为字典信息 username -->value,realname -->text
     * @return
     */
    List<DictModel> queryAllUserBackDictModel();

    List<DictModel> queryTableDictItemsByCodeAndFilter(String table, String text, String code, String filterSql);

    List<String> queryTableDictByKeys(String table, String text, String code, String[] keyArray);
}
