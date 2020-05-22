package com.ta.platform.common.convert;

import com.ta.platform.common.vo.LoginUserRedisVo;
import com.ta.platform.common.vo.LoginUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Creator: zhuji
 * Date: 5/21/2020
 * Time: 11:53 AM
 * Description:
 */
@Mapper
public interface LoginUserRedisVoConvert {
    LoginUserRedisVoConvert INSTANCE = Mappers.getMapper(LoginUserRedisVoConvert.class);

    LoginUserRedisVo toLoginUserRedisVo(LoginUserVo loginUserVo);
}
