
/**
 * 系统项目名称
 * com.supermarket.system.web.dao
 * IRoleDao.java
 * 
 * 2015年6月30日-上午11:24:47
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.system.web.model.TblRole;


/**
 * @description 角色信息持久化接口
 * 
 * @time 2015年6月30日 上午11:24:47
 * @author Jason
 * @version 1.0.0
 * 
 */
@Repository("roleDao")
public interface IRoleDao {
    /**
     * @description findMenuByRoleId 查询角色权限列表
     * @param roleId 角色
     * @return List
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List findMenuByRoleId(Long roleId);
    /**
     * @description deleteAuthority 删除角色权限
     * @param roleId 角色
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void deleteAuthority(Long roleId);
    /**
     * @description deleteAuthorityByMenuId 删除角色权限
     * @param menuId 菜单
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void deleteAuthorityByMenuId(Long menuId);
    /**
     * @description saveAuthorityBatch 批量插入角色权限
     * @param list 角色权限
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void saveAuthorityBatch(List list);
    /**
     * @description findAllRoleWithoutPaging 查询所有角色(不分页)
     * @return List
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List findAllRoleWithoutPaging();
    /**
     * @description updateRole 修改角色
     * @param role 
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void updateRole(TblRole role);
    /**
     * @description addNewRole 新增角色
     * @param role 
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void addNewRole(TblRole role);
    /**
     * @description deleteRoleByRoleId 删除角色
     * @param roleId 角色
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void deleteRoleByRoleId(Long roleId);
    
}
