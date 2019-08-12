package com.ta.platform.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ta.platform.modules.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	@Select("select role_code from t_sys_role where id in (select role_id from t_sys_user_role where user_id = (select id from t_sys_user where username=#{username}))")
	List<String> getRoleByUserName(@Param("username") String username);
}
