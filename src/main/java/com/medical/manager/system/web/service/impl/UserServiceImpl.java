/**
 * 系统项目名称
 * com.supermarket.system.web.service.impl
 * UserServiceImpl.java
 * 
 * 2015年6月29日-下午3:04:20
 * 2015版权所有
 * 
 */

package com.medical.manager.system.web.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.util.MD5Util;
import com.medical.manager.system.web.dao.IMenuDao;
import com.medical.manager.system.web.dao.IUserDao;
import com.medical.manager.system.web.dao.IUserSecretDao;
import com.medical.manager.system.web.model.TblMenu;
import com.medical.manager.system.web.model.TblUser;
import com.medical.manager.system.web.model.TblUserRole;
import com.medical.manager.system.web.model.TblUserSecret;
import com.medical.manager.system.web.service.IUserService;

/**
 * @description 用户实现
 * 
 * @time 2015年6月29日 下午3:04:20
 * @author Jason
 * @version 1.0.0
 * 
 */
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements IUserService {
    private Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Resource
    private IBaseDao<TblUser> baseDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private IUserSecretDao userSecretDao;
    @Resource
    private IMenuDao menuDao;

    public TblUser findUserById(Long id) {
        return userDao.findUserById(id);
    }

    public List findUsers(TblUser user) {
        return userDao.findUsers(user);
    }

    @Override
    public Map doLogin(String userCode, String password) throws Exception {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            TblUser user = userDao.findUserByUserCode(userCode);
            if (user == null) {
                responseMap.put("success", false);
                responseMap.put("msg", "用户不存在！");
                return responseMap;
            }
            if(CommonEnums.TblUserStatus.DELETED.id.equals(user.getCurrentStatus())){
                responseMap.put("success", false);
                responseMap.put("msg", "用户已被禁用！");
                return responseMap;
            }
            TblUserSecret secret = userSecretDao.findByUserCode(userCode);
            if (secret == null) {
                responseMap.put("success", false);
                responseMap.put("msg", "密码不存在！");
                return responseMap;
            }
//            if (!MD5Util.md5Encode(password, userCode).equals(secret.getUserPassword())) {
//                responseMap.put("success", false);
//                responseMap.put("msg", "用户名或密码不正确！");
//                return responseMap;
//            }
            responseMap.put("success", true);
            responseMap.put("user", user);
            //查询用户有权限的菜单信息
            List<TblMenu> menuList = menuDao.findByUserCode(userCode);
            responseMap.put("menuList", menuList);
        } catch (Exception e) {
            logger.error("异常！" + e.getMessage(), e);
            throw e;
        }
        return responseMap;
    }

    @Override
    public Map doModifyPassword(String userCode, String oldPass, String newPass) throws Exception {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            TblUser user = userDao.findUserByUserCode(userCode);
            if (user == null) {
                responseMap.put("success", false);
                responseMap.put("msg", "用户不存在！");
                return responseMap;
            }
            TblUserSecret secret = userSecretDao.findByUserCode(userCode);
            if (secret == null) {
                responseMap.put("success", false);
                responseMap.put("msg", "密码不存在！");
                return responseMap;
            }
            if (!MD5Util.md5Encode(oldPass, userCode).equals(secret.getUserPassword())) {
                responseMap.put("success", false);
                responseMap.put("msg", "原密码不正确！");
                return responseMap;
            }
            secret.setUserPassword(MD5Util.md5Encode(newPass, userCode));
            secret.setModifyDate(DateUtil.fmtDateToStr(new Date(), "yyyyMMdd"));
            secret.setModifyTime(DateUtil.fmtDateToStr(new Date(), "HHmmss"));
            secret.setModifyOper(userCode);
            userSecretDao.updateSecret(secret);
            responseMap.put("success", true);
            responseMap.put("msg", "密码修改成功！");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        return responseMap;
    }

    @Override
    public List<TblUser> queryUserWithPaging(Integer pageNum, Integer pageSize, Map paramMap) {
        return baseDao.queryWithPage(paramMap, pageNum, pageSize, "findUserWithPaging");
    }

    @Override
    public List queryUserRoleList(Long userId) {
        return userDao.findUserRoleByUserId(userId);
    }

    @Override
    public void doSaveNewUser(TblUser user, String userRoleId, String userPassword) {
        TblUser existUser = userDao.findUserByUserCode(user.getUserCode());
        if (existUser != null) {
            throw new RuntimeException("用户名已存在！");
        }
        user.setCurrentStatus(CommonEnums.TblUserStatus.VALID.id);
        userDao.addNewUser(user);
        // 增加用户密码
        TblUserSecret secret = new TblUserSecret();
        secret.setCreateDate(user.getCreateDate());
        secret.setCreateOper(user.getCreateOper());
        secret.setCreateTime(user.getCreateTime());
        secret.setModifyDate(user.getModifyDate());
        secret.setModifyOper(user.getModifyOper());
        secret.setModifyTime(user.getModifyTime());
        secret.setRemark("密码初始化");
        secret.setUserCode(user.getUserCode());
        secret.setUserId(user.getId());
        try {
            secret.setUserPassword(MD5Util.md5Encode(userPassword, user.getUserCode()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        userSecretDao.addNewSecret(secret);
        TblUserRole userRole = new TblUserRole();
        userRole.setCreateDate(user.getCreateDate());
        userRole.setCreateOper(user.getCreateOper());
        userRole.setCreateTime(user.getCreateTime());
        userRole.setModifyDate(user.getModifyDate());
        userRole.setModifyOper(user.getModifyOper());
        userRole.setModifyTime(user.getModifyTime());
        userRole.setRemark("角色初始化");
        userRole.setRoleId(NumberUtils.toLong(userRoleId));
        userRole.setUserCode(user.getUserCode());
        userRole.setUserId(user.getId());
        userDao.addNewUserRole(userRole);
    }

    @Override
    public void doUpdateUser(TblUser user) {
        userDao.updateUser(user);
    }
    
    @Override
    public void doUpdateUser(TblUser user, String userRoleId, String userPassword) {
        if(userPassword != null && !"".equals(userPassword.trim())){
            TblUserSecret secret = new TblUserSecret();
            secret.setUserCode(user.getUserCode());
            secret.setUserId(user.getId());
            secret.setModifyDate(user.getModifyDate());
            secret.setModifyOper(user.getModifyOper());
            secret.setModifyTime(user.getModifyTime());
            try {
                secret.setUserPassword(MD5Util.md5Encode(userPassword, user.getUserCode()));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            userSecretDao.updateSecret(secret);
        }
        List<TblUserRole> userRoleList = userDao.findUserRoleByUserId(user.getId());
        if(userRoleList != null && !userRoleList.isEmpty()){
            TblUserRole userRole = userRoleList.get(0);
            userRole.setRoleId(NumberUtils.toLong(userRoleId));
            userRole.setModifyDate(user.getModifyDate());
            userRole.setModifyOper(user.getModifyOper());
            userRole.setModifyTime(user.getModifyTime());
            userDao.updateUserRoleById(userRole);
        }
        userDao.updateUser(user);
    }

    @Override
    public void doDeleteUser(Long id) {
        userDao.deleteUser(id);
        // 删除用户密码，用户角色
        userSecretDao.deleteUserSecretByUserId(id);
        userDao.deleteUserRoleByUserId(id);
    }

    @Override
    public void doUpdateUserRole(TblUserRole userRole) {
        userDao.updateUserRoleById(userRole);
    }

    @Override
    public void doSaveUserRole(TblUserRole userRole) {
        userDao.addNewUserRole(userRole);
    }

    @Override
    public void doInvalidUser(String userIds) throws Exception {
        List<TblUser> userList = userDao.findUserByIds(Arrays.asList(userIds.split(",")));
        if(userList == null || userList.isEmpty()){
            throw new Exception("未查询到指定用户！");
        }
        for(TblUser user : userList){
            String status = user.getCurrentStatus();
            if(CommonEnums.TblUserStatus.DELETED.id.equals(status)){
                throw new Exception("管理员【"+ user.getUserName() +"】已禁用，请勿重复操！");
            }
            user.setCurrentStatus(CommonEnums.TblUserStatus.DELETED.id);
        }
        userDao.updateUsersStatus(userList);
    }
    
    @Override
    public void doValidUser(String userIds) throws Exception {
        List<TblUser> userList = userDao.findUserByIds(Arrays.asList(userIds.split(",")));
        if(userList == null || userList.isEmpty()){
            throw new Exception("未查询到指定用户！");
        }
        for(TblUser user : userList){
            String status = user.getCurrentStatus();
            if(CommonEnums.TblUserStatus.VALID.id.equals(status)){
                throw new Exception("管理员【"+ user.getUserName() +"】已启用，请勿重复操！");
            }
            user.setCurrentStatus(CommonEnums.TblUserStatus.VALID.id);
        }
        userDao.updateUsersStatus(userList);
    }

    @Override
    public void doUpdateUserLastLogin(TblUser user) {
        userDao.updateUserLastLogin(user);
    }

    @Override
    public void doDeleteUsers(String userIds) throws Exception {
        userDao.deleteUsers(Arrays.asList(userIds.split(",")));
        // 删除用户密码，用户角色
        userSecretDao.deleteUserSecretByUserIds(Arrays.asList(userIds.split(",")));
        userDao.deleteUserRoleByUserIds(Arrays.asList(userIds.split(",")));
    }
    
}
