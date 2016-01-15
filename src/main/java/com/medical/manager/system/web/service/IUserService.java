
/**
 * 系统项目名称
 * com.supermarket.system.web.service
 * IUserService.java
 * 
 * 2015年6月29日-下午3:03:34
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.system.web.model.TblUser;
import com.medical.manager.system.web.model.TblUserRole;


/**
 * @description 用户信息接口
 * 
 * @time 2015年6月29日 下午3:03:34
 * @author Jason
 * @version 1.0.0
 * 
 */
public interface IUserService {
    /**
     * @description findUserById 主键查询
     * @param id 主键
     * @return TblUser
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public TblUser findUserById(Long id);
    /**
     * @description findUsers 条件查询
     * @param user 查询条件
     * @return List
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List findUsers(TblUser user);
    
    /**
     * @description doLogin 用户登录
     * @param userCode 用户名
     * @param password 密码
     * @return Map
     * @throws Exception 
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public Map doLogin(String userCode, String password) throws Exception;
    /**
     * @description doModifyPassword 修改用户密码
     * @param userCode 用户名
     * @param oldPass 原密码
     * @param newPass 新密码
     * @return Map
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public Map doModifyPassword(String userCode, String oldPass, String newPass) throws Exception;
    /**
     * @description queryUserWithPaging 分页查询用户列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return List<TblUser>
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List<TblUser> queryUserWithPaging(Integer pageNum, Integer pageSize, Map paramMap);
    /**
     * @description queryUserRoleList 查询用户角色
     * @param userId 用户
     * @return List
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List queryUserRoleList(Long userId);
    /**
     * @description doSaveNewUser 保存新用户
     * @param user 用户
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void doSaveNewUser(TblUser user, String userRoleId, String userPassword);
    /**
     * @description doUpdateUser 更新用户
     * @param user 用户
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void doUpdateUser(TblUser user);
    /**
     * @description doUpdateUser 更新用户
     * @param user 用户
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void doUpdateUser(TblUser user, String userRoleId, String userPassword);
    /**
     * @description doUpdateUser 更新用户上次登录信息
     * @param user 用户
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void doUpdateUserLastLogin(TblUser user);
    /**
     * @description doDeleteUser 删除用户
     * @param id 用户主键
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void doDeleteUser(Long id);
    /**
     * @description doDeleteUsers 删除用户
     * @param id 用户主键
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void doDeleteUsers(String userIds) throws Exception;
    /**
     * doInvalidUser 禁用用户
     * (这里描述这个方法适用条件 – 可选)
     * @param userIds 用户ID
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    public void doInvalidUser(String userIds) throws Exception;
    
    /**
     * doValidUser 启用用户
     * (这里描述这个方法适用条件 – 可选)
     * @param userIds 用户ID
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    public void doValidUser(String userIds) throws Exception;
    
    /**
     * @description doUpdateUserRole 更新用户角色
     * @param userRole 用户角色
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void doUpdateUserRole(TblUserRole userRole);
    /**
     * @description doSaveUserRole 新增用户角色
     * @param userRole 用户角色
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void doSaveUserRole(TblUserRole userRole);
}
