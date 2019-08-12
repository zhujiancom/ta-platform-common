package com.ta.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ey.tax.toolset.core.StrUtil;
import com.ta.platform.common.system.model.LoginUserInfo;
import com.ta.platform.modules.system.entity.SysPermission;
import com.ta.platform.modules.system.entity.SysUser;
import com.ta.platform.modules.system.entity.SysUserRole;
import com.ta.platform.modules.system.mapper.SysPermissionMapper;
import com.ta.platform.modules.system.mapper.SysUserMapper;
import com.ta.platform.modules.system.mapper.SysUserRoleMapper;
import com.ta.platform.modules.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @Author: scott
 * @Date: 2018-12-20
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
	
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public SysUser getUserByName(String username) {
		return userMapper.getUserByName(username);
	}
	
	
	@Override
	@Transactional
	public void addUserWithRole(SysUser user, String roles) {
		this.save(user);
		if(StrUtil.isNotEmpty(roles)) {
			String[] arr = roles.split(",");
			for (String roleId : arr) {
				SysUserRole userRole = new SysUserRole(user.getId(), roleId);
				sysUserRoleMapper.insert(userRole);
			}
		}
	}

	@Override
	@CacheEvict(value="loginUser_cacheRules", allEntries=true)
	@Transactional
	public void editUserWithRole(SysUser user, String roles) {
		this.updateById(user);
		//先删后加
		sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, user.getId()));
		if(StrUtil.isNotEmpty(roles)) {
			String[] arr = roles.split(",");
			for (String roleId : arr) {
				SysUserRole userRole = new SysUserRole(user.getId(), roleId);
				sysUserRoleMapper.insert(userRole);
			}
		}
	}


	@Override
	public List<String> getRole(String username) {
		return sysUserRoleMapper.getRoleByUserName(username);
	}
	
	/**
	 * 通过用户名获取用户角色集合
	 * @param username 用户名
     * @return 角色集合
	 */
	@Override
	@Cacheable(value = "loginUser_cacheRules",key = "'Roles_'+#username")
	public Set<String> getUserRolesSet(String username) {
		// 查询用户拥有的角色集合
		List<String> roles = sysUserRoleMapper.getRoleByUserName(username);
		log.info("-------通过数据库读取用户拥有的角色Rules------username： " + username + ",Roles size: " + (roles == null ? 0 : roles.size()));
		return new HashSet<>(roles);
	}

	/**
	 * 通过用户名获取用户权限集合
	 *
	 * @param username 用户名
	 * @return 权限集合
	 */
	@Override
	@Cacheable(value = "loginUser_cacheRules",key = "'Permissions_'+#username")
	public Set<String> getUserPermissionsSet(String username) {
		Set<String> permissionSet = new HashSet<>();
		List<SysPermission> permissionList = sysPermissionMapper.queryByUser(username);
		for (SysPermission po : permissionList) {
//			// TODO URL规则有问题？
//			if (oConvertUtils.isNotEmpty(po.getUrl())) {
//				permissionSet.add(po.getUrl());
//			}
			if (StrUtil.isNotEmpty(po.getPerms())) {
				permissionSet.add(po.getPerms());
			}
		}
		log.info("-------通过数据库读取用户拥有的权限Perms------username： "+ username+",Perms size: "+ (permissionSet==null?0:permissionSet.size()) );
		return permissionSet;
	}

	@Override
	public LoginUserInfo getCacheUser(String username) {
		LoginUserInfo info = new LoginUserInfo();
//		info.setOneDepart(true);
////		SysUser user = userMapper.getUserByName(username);
////		info.setSysUserCode(user.getUsername());
////		info.setSysUserName(user.getRealname());
//
//
//		LoginUser user = sysBaseAPI.getUserByName(username);
//		if(user!=null) {
//			info.setSysUserCode(user.getUsername());
//			info.setSysUserName(user.getRealname());
//			info.setSysOrgCode(user.getOrgCode());
//		}
//
//		//多部门支持in查询
//		List<SysDepart> list = sysDepartMapper.queryUserDeparts(user.getId());
//		List<String> sysMultiOrgCode = new ArrayList<String>();
//		if(list==null || list.size()==0) {
//			//当前用户无部门
//			//sysMultiOrgCode.add("0");
//		}else if(list.size()==1) {
//			sysMultiOrgCode.add(list.get(0).getOrgCode());
//		}else {
//			info.setOneDepart(false);
//			for (SysDepart dpt : list) {
//				sysMultiOrgCode.add(dpt.getOrgCode());
//			}
//		}
//		info.setSysMultiOrgCode(sysMultiOrgCode);
		
		return info;
	}

	// 根据部门Id查询
	@Override
	public IPage<SysUser> getUserByDepId(Page<SysUser> page, String departId, String username) {
		return userMapper.getUserByDepId(page, departId,username);
	}


	// 根据角色Id查询
	@Override
	public IPage<SysUser> getUserByRoleId(Page<SysUser> page, String roleId, String username) {
		return userMapper.getUserByRoleId(page,roleId,username);
	}


	@Override
	public void updateUserDepart(String username,String orgCode) {
		baseMapper.updateUserDepart(username, orgCode);
	}

}
