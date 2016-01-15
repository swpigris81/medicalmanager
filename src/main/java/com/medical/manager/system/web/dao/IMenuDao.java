
/**
 * 系统项目名称
 * com.supermarket.system.web.dao
 * IMenuDao.java
 * 
 * 2015年6月30日-上午11:27:43
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.system.web.model.TblMenu;


/**
 * @description 菜单信息持久化接口
 * 
 * @time 2015年6月30日 上午11:27:43
 * @author Jason
 * @version 1.0.0
 * 
 */
@Repository("menuDao")
public interface IMenuDao {
    
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
     * @param map{menuId 菜单编号, menuLevel 菜单级别，为空，则查询所有数据}
     * @return List
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List findChildMenuByMenuId(Map map);
    /**
     * findChildMenuByMenuIdForManager 查询子菜单,包括隐藏的
     * (这里描述这个方法适用条件 – 可选)
     * @param map map{menuId 菜单编号, menuLevel 菜单级别，为空，则查询所有数据}
     * @return List
     * @exception 
     * @since  1.0.0
     */
    public List findChildMenuByMenuIdForManager(Map map);
    /**
     * @description findAll 查询所有菜单列表
     * @return List
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List findAllParentMenu();
    /**
     * @description addNewMenu 新增菜单
     * @param menu 菜单
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void addNewMenu(TblMenu menu);
    /**
     * @description updateMenu 修改菜单
     * @param menu 菜单
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void updateMenu(TblMenu menu);
    /**
     * @description deleteMenuByMenuId 删除菜单
     * @param menuId 菜单编号
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void deleteMenuByMenuId(Long menuId);
}
