package com.ta.platform.common.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ta.platform.common.modules.system.entity.SysDict;
import com.ta.platform.common.modules.system.model.DuplicateBean;
import com.ta.platform.common.system.model.DictModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 字典表 Mapper 接口
 */
public interface SysDictMapper extends BaseMapper<SysDict> {
    /**
     *  重复检查SQL
     * @return
     */
    Long duplicateCheckCountSql(DuplicateBean duplicateCheckBean);

    Long duplicateCheckCountSqlNoDataId(DuplicateBean duplicateCheckVo);

    List<DictModel> queryDictItemsByCode(@Param("code") String code);

    List<DictModel> queryTableDictItemsByCode(@Param("table") String table,@Param("text") String text,@Param("code") String code);

    String queryDictTextByKey(@Param("code") String code,@Param("key") String key);

    String queryTableDictTextByKey(@Param("table") String table,@Param("text") String text,@Param("code") String code,@Param("key") String key);

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
}
