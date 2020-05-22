package com.ta.platform.common.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ta.platform.common.module.entity.SysDictItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 3:06 PM
 * Description:
 */
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {
    @Select("SELECT * FROM T_SYS_DICT_ITEM WHERE DICT_ID = #{mainId}")
    List<SysDictItem> selectItemsByMainId(String mainId);
}
