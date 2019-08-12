package com.ta.platform.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ta.platform.common.exception.PlatformException;
import com.ta.platform.modules.system.entity.SysPermission;
import com.ta.platform.modules.system.model.TreeModel;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 */
public interface ISysPermissionService extends IService<SysPermission> {
	
	public List<TreeModel> queryListByParentId(String parentId);
	
	/**真实删除*/
	public void deletePermission(String id) throws PlatformException;
	/**逻辑删除*/
	public void deletePermissionLogical(String id) throws PlatformException;
	
	public void addPermission(SysPermission sysPermission) throws PlatformException;
	
	public void editPermission(SysPermission sysPermission) throws PlatformException;
	
	public List<SysPermission> queryByUser(String username);
	
	/**
	 * 根据permissionId删除其关联的SysPermissionDataRule表中的数据
	 * 
	 * @param id
	 * @return
	 */
	public void deletePermRuleByPermId(String id);
	
	/**
	  * 查询出带有特殊符号的菜单地址的集合
	 * @return
	 */
	public List<String> queryPermissionUrlWithStar();
}
