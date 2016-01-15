
/**
 * 系统项目名称
 * com.supermarket.system.web.action
 * UserHandler.java
 * 
 * 2015年6月30日-上午11:55:35
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.system.web.model.TblSysProvince;
import com.medical.manager.system.web.model.TblUser;
import com.medical.manager.system.web.model.TblUserRole;
import com.medical.manager.system.web.service.IRoleService;
import com.medical.manager.system.web.service.ISysAdministrativeService;
import com.medical.manager.system.web.service.IUserService;


/**
 * @description 
 * 
 * @time 2015年6月30日 上午11:55:35
 * @author Jason
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/user")
public class UserHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(UserHandler.class);
    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private ISysAdministrativeService sysAdministrativeService;
    @RequestMapping("/index.do")
    public String index(HttpSession session, Model model){
        String timeOut = logginTimeOut(session);
        if(timeOut != null){
            return timeOut;
        }
        List roleList = roleService.queryAllRole();
        List<TblSysProvince> provinceList = sysAdministrativeService.findAllProvince();
        model.addAttribute("roleList", roleList);
        model.addAttribute("provinceList", provinceList);
        return "system/user/index";
    }
    /**
     * @description userList 用户列表
     * @param response
     * @param page 当前页码
     * @param rows 每页数量
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    @RequestMapping("userList.do")
    public void userList(HttpServletResponse response, Integer page, Integer rows, TblUser user, String roleId){
        logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
        Map paramMap = new HashMap();
        if(user != null){
            try {
                paramMap = BeanUtils.describe(user);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        if(!StringUtils.isEmpty(roleId)){
            paramMap.put("roleId", roleId);
        }
        List<TblUser> userList = userService.queryUserWithPaging(page, rows, paramMap);
        writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblUser>) userList)));
    }
    /**
     * @description userRoleList 查询用户角色信息
     * @param response
     * @param userId 用户ID
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    @RequestMapping("userRoleList.do")
    public void userRoleList(HttpServletResponse response, Long userId){
        logger.info("查询用户：" + userId + " 的角色信息。");
        List list = userService.queryUserRoleList(userId);
        writeSuccessMsg(response, JSON.toJSONString(list));
    }
    
    /**
     * @description saveUser 新增/修改用户，新增用户时，设置其默认密码
     * @param session
     * @param response
     * @param user 用户
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    @RequestMapping("saveUser.do")
    public void saveUser(HttpSession session, HttpServletResponse response, 
            TblUser user, String userRoleId, String userPassword){
        if(user != null){
            logger.info("保存用户信息：" + user.toString());
            user.setModifyDate(DateUtil.getNowDate());
            user.setModifyOper(getSessionUser(session).getUserCode());
            user.setModifyTime(DateUtil.getNowTime());
            try{
                if(user.getId() != null){
                    //修改
                    userService.doUpdateUser(user, userRoleId, userPassword);
                    writeSuccessMsg(response, "用户信息保存成功。");
                }else{
                    //新增
                    user.setCreateDate(DateUtil.getNowDate());
                    user.setCreateOper(getSessionUser(session).getUserCode());
                    user.setCreateTime(DateUtil.getNowTime());
                    userService.doSaveNewUser(user, userRoleId, userPassword);
                    writeSuccessMsg(response, "用户信息保存成功");
                }
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                writeFailMsg(response, "用户保存失败！" + e.getMessage());
            }
        }else{
            writeFailMsg(response, "用户保存失败！用户信息不能为空！");
        }
    }
    /**
     * @description deleteUser 删除用户
     * @param session
     * @param response
     * @param id 用户
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    @RequestMapping("deleteUser.do")
    public void deleteUser(HttpSession session, 
            HttpServletResponse response, String userIds){
        try{
            if(StringUtils.isEmpty(userIds)){
                throw new Exception("用户信息为空！");
            }
            userService.doDeleteUsers(userIds);
            writeSuccessMsg(response, "管理员信息删除成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            writeFailMsg(response, "管理员删除失败！" + e.getMessage());
        }
    }
    /**
     * invalidUser 禁用管理员
     * (这里描述这个方法适用条件 – 可选)
     * @param response 响应
     * @param userIds 用户ID
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("invalidUser.do")
    public void invalidUser(HttpServletResponse response, String userIds){
        logger.info("禁用用户");
        try{
            if(userIds == null || "".equals(userIds)){
                throw new Exception("禁用管理员为空，禁用失败！");
            }
            userService.doInvalidUser(userIds);
            writeSuccessMsg(response, "所选用户禁用成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            writeFailMsg(response, e.getMessage());
        }
    }
    
    /**
     * validUser 启用管理员
     * (这里描述这个方法适用条件 – 可选)
     * @param response 响应
     * @param userIds 用户ID
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("validUser.do")
    public void validUser(HttpServletResponse response, String userIds){
        logger.info("启用用户");
        try{
            if(userIds == null || "".equals(userIds)){
                throw new Exception("启用管理员为空，禁用失败！");
            }
            userService.doValidUser(userIds);
            writeSuccessMsg(response, "所选用户启用成功！");
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            writeFailMsg(response, e.getMessage());
        }
    }
    
    /**
     * @description saveUserRole 保存用户角色
     * @param response
     * @param userRole 角色
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    @RequestMapping("saveUserRole.do")
    public void saveUserRole(HttpSession session, HttpServletResponse response, TblUserRole userRole){
        if(userRole != null){
            logger.info("保存用户角色：" + userRole.toString());
            try{
                userRole.setModifyDate(DateUtil.getNowDate());
                userRole.setModifyOper(getSessionUser(session).getUserCode());
                userRole.setModifyTime(DateUtil.getNowTime());
                if(userRole.getId() != null){
                    //修改
                    userService.doUpdateUserRole(userRole);
                }else{
                    //更新
                    userRole.setCreateDate(DateUtil.getNowDate());
                    userRole.setCreateOper(getSessionUser(session).getUserCode());
                    userRole.setCreateTime(DateUtil.getNowTime());
                    userService.doSaveUserRole(userRole);
                }
                writeSuccessMsg(response, "用户角色保存成功！");
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                writeFailMsg(response, "用户角色保存失败！" + e.getMessage());
            }
        }else{
            writeFailMsg(response, "用户角色保存失败，用户角色信息不能为空！");
        }
    }
    /**
     * @description modifyPassword 修改用户密码
     * @param oldPass 原密码
     * @param newPass  新密码
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    @RequestMapping("modifyPassword.do")
    public void modifyPassword(HttpServletRequest req, HttpServletResponse response, String oldPass, String newPass){
        TblUser user = (TblUser) req.getSession().getAttribute(SESSION_USER);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(user == null){
            writeFailMsg(response, "登录已超时，请重新登录！");
            return;
        }
        try{
            resultMap = userService.doModifyPassword(user.getUserCode(), oldPass, newPass);
            writeMsg(response, resultMap);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            writeFailMsg(response, "系统异常！" + e.getMessage());
        }
    }
}
