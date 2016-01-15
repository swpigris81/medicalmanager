
/**
 * 系统项目名称
 * com.supermarket.system.web.service
 * IMenuService.java
 * 
 * 2015年7月2日-上午11:30:20
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.service;

import java.util.List;

import com.medical.manager.system.web.model.TblMenu;


/**
 * @description 菜单服务接口
 * 
 * @time 2015年7月2日 上午11:30:20
 * @author Jason
 * @version 1.0.0
 * 
 */

public interface IMenuService {
    /**
     * @description findByUserCode 查询用户有权限的菜单
     * @param userCode 用户名
     * @return List
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List findByUserCode(String userCode);
    /**
     * @description findFirstLevelMenuByUserCode 查询用户第一级菜单
     * @param userCode 用户名
     * @return List
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List findFirstLevelMenuByUserCode(String userCode);
    /**
     * @description findChildMenuByMenuId 查询子菜单
     * @param menuId 菜单编号
     * @return List
     * @throws Exception 
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List findChildMenuByMenuId(Long menuId, String userCode);
    /**
     * @description findAllMenuListGrid 查询所有菜单信息，用于展示菜单管理列表
     * @return List 菜单列表
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List findAllMenuListGrid();
    /**
     * @description saveNewMenu 新增菜单
     * @param menu 菜单
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void saveNewMenu(TblMenu menu);
    /**
     * @description saveOldMenu 修改菜单
     * @param menu 菜单
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void saveOldMenu(TblMenu menu);
    /**
     * @description doDeleteMenu 删除菜单
     * @param menuId 菜单编号
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void doDeleteMenu(Long menuId);
}
