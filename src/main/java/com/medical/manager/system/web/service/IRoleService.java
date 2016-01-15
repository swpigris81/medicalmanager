/**
 * 系统项目名称
 * com.supermarket.system.web.service
 * IRoleService.java
 * 
 * 2015年7月7日-上午11:57:23
 * 2015版权所有
 * 
 */

package com.medical.manager.system.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.system.web.model.TblRole;

/**
 * @description
 * 
 * @time 2015年7月7日 上午11:57:23
 * @author Jason
 * @version 1.0.0
 * 
 */

public interface IRoleService {
    /**
     * @description queryRoleListWithPaging 分页查询角色列表
     * @param pageNum
     *            页码
     * @param pageSize
     *            每页数量
     * @return List<TblRole>
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public List<TblRole> queryRoleListWithPaging(Integer pageNum, Integer pageSize);
    /**
     * @description queryRoleListWithPaging 分页查询角色列表
     * @param pageNum
     *            页码
     * @param pageSize
     *            每页数量
     * @return List<TblRole>
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public List<TblRole> queryRoleListWithPaging(Map paramMap, Integer pageNum, Integer pageSize);

    /**
     * @description queryAllRole 查询所有角色
     * @return List
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public List queryAllRole();

    /**
     * @description roleAuthorityList 查询角色权限列表
     * @param roleId
     *            角色编号
     * @return List
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public List roleAuthorityList(Long roleId);

    /**
     * @description saveAuthority 保存角色权限
     * @param roleId
     *            角色
     * @param menuId
     *            菜单
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void saveAuthority(Long roleId, String[] menuId);

    /**
     * @description saveNewRole 新增角色
     * @param role
     *            角色
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void saveNewRole(TblRole role);

    /**
     * @description doUpdateRole 更新角色
     * @param role
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void doUpdateRole(TblRole role);

    /**
     * @description doDeleteRole 删除角色
     * @param roleId
     *            角色
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void doDeleteRole(Long roleId);
}
