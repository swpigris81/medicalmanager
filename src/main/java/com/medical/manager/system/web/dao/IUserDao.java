/**
 * 系统项目名称
 * com.supermarket.system.web.dao
 * IUserDao.java
 * 
 * 2015年6月29日-下午3:04:41
 * 2015版权所有
 * 
 */

package com.medical.manager.system.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.system.web.model.TblUser;
import com.medical.manager.system.web.model.TblUserRole;

/**
 * @description 用户数据持久化接口
 * 
 * @time 2015年6月29日 下午3:04:41
 * @author Jason
 * @version 1.0.0
 * 
 */
@Repository(value = "userDao")
public interface IUserDao {
    /**
     * @description findUserById 主键查询
     * @param id
     *            主键
     * @return TblUser
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public TblUser findUserById(Long id);
    /**
     * findUserByIds 批量查询用户信息
     * (这里描述这个方法适用条件 – 可选)
     * @param userIdList 用户主键列表
     * @return List<TblUser> 用户信息
     * @exception 
     * @since  1.0.0
     */
    public List<TblUser> findUserByIds(List userIdList);

    /**
     * @description findUserByUserCode 用户名查询
     * @param userCode
     *            用户名
     * @return TblUser
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public TblUser findUserByUserCode(String userCode);

    /**
     * @description findUsers 条件查询
     * @param user
     *            查询条件
     * @return List
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public List findUsers(TblUser user);

    /**
     * @description findUserRoleByUserId 查询用户角色
     * @param userId
     *            用户
     * @return List
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public List findUserRoleByUserId(Long userId);

    /**
     * @description deleteUserRoleByRoleId 删除用户角色信息
     * @param roleId
     *            角色
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void deleteUserRoleByRoleId(Long roleId);

    /**
     * @description deleteUserRoleByUserId 删除用户角色信息
     * @param userId
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void deleteUserRoleByUserId(Long userId);
    /**
     * @description deleteUserRoleByUserId 删除用户角色信息
     * @param userId
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void deleteUserRoleByUserIds(List userList);

    /**
     * @description addNewUser 新增用户
     * @param user
     *            用户
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void addNewUser(TblUser user);

    /**
     * @description updateUser 修改用户
     * @param user
     *            用户
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void updateUser(TblUser user);
    /**
     * updateUsersStatus 批量更新用户状态
     * (这里描述这个方法适用条件 – 可选)
     * @param userList 用户
     * @exception 
     * @since  1.0.0
     */
    public void updateUsersStatus(List userList);
    /**
     * doUpdateUserLastLogin 更新上次登录信息
     * (这里描述这个方法适用条件 – 可选)
     * @param user 
     * @exception 
     * @since  1.0.0
     */
    public void updateUserLastLogin(TblUser user);

    /**
     * @description deleteUser 删除用户
     * @param id
     *            用户主键
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void deleteUser(Long id);
    /**
     * @description deleteUsers 删除用户
     * @param id
     *            用户主键
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void deleteUsers(List userIdList);

    /**
     * @description updateUserRoleById 更新用户角色
     * @param userRole
     *            用户角色
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void updateUserRoleById(TblUserRole userRole);

    /**
     * @description addNewUserRole 添加用户角色
     * @param userRole
     *            添加角色
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public void addNewUserRole(TblUserRole userRole);
}
