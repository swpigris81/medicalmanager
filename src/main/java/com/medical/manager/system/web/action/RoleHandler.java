/**
 * 系统项目名称
 * com.supermarket.system.web.action
 * RoleHandler.java
 * 
 * 2015年7月7日-上午11:37:42
 * 2015版权所有
 * 
 */

package com.medical.manager.system.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.system.web.model.TblRole;
import com.medical.manager.system.web.service.IRoleService;

/**
 * @description 角色管理
 * 
 * @time 2015年7月7日 上午11:37:42
 * @author Jason
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/role")
public class RoleHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(RoleHandler.class);
    @Resource
    private IRoleService roleService;

    /**
     * @description index 角色管理首页
     * @return String
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("index.do")
    public String index(HttpSession session) {
        String timeOut = logginTimeOut(session);
        if (timeOut != null) {
            return timeOut;
        }
        return "system/role/index";
    }

    /**
     * @description roleList 查询角色列表
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("roleList.do")
    public void roleList(HttpServletResponse response, Integer page, Integer rows, String roleName) {
        logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("roleName", roleName);
        List<TblRole> roleList = roleService.queryRoleListWithPaging(paramMap, page, rows);
        writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblRole>) roleList)));
    }

    /**
     * @description roleRightList 查询角色菜单
     * @param response
     * @param roleId
     *            角色ID
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("roleAuthorityList.do")
    public void roleAuthorityList(HttpServletResponse response, Long roleId) {
        logger.info("查询角色：" + roleId + "的权限列表");
        List list = roleService.roleAuthorityList(roleId);
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("success", true);
        responseMap.put("authorityData", list);
        writeMsg(response, responseMap);
    }

    /**
     * @description saveAuthority 保存角色权限
     * @param response
     * @param roleId
     *            角色编号
     * @param roleMenu
     *            菜单编号
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("saveAuthority")
    public void saveAuthority(HttpServletResponse response, Long roleId, String[] roleMenu) {
        logger.info("保存角色：" + roleId + "的权限列表");
        try {
            roleService.saveAuthority(roleId, roleMenu);
            writeSuccessMsg(response, "角色权限已保存成功！");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            writeFailMsg(response, "角色权限保存失败，" + e.getMessage());
        }
    }

    /**
     * @description saveRole 保存角色信息
     * @param response
     * @param role
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("saveRole.do")
    public void saveRole(HttpSession session, HttpServletResponse response, TblRole role, String[] roleMenu) {
        if (role != null) {
            logger.info("保存角色信息：" + role.toString());
            role.setModifyDate(DateUtil.getNowDate());
            role.setModifyOper(getSessionUser(session).getUserCode());
            role.setModifyTime(DateUtil.getNowTime());
            if (role.getRoleId() != null) {
                // 修改
                roleService.doUpdateRole(role);
                roleService.saveAuthority(role.getRoleId(), roleMenu);
            } else {
                // 新增
                role.setCreateDate(DateUtil.getNowDate());
                role.setCreateOper(getSessionUser(session).getUserCode());
                role.setCreateTime(DateUtil.getNowTime());
                roleService.saveNewRole(role);
                roleService.saveAuthority(role.getRoleId(), roleMenu);
            }
            writeSuccessMsg(response, "角色信息保存成功！");
        } else {
            writeFailMsg(response, "角色信息保存失败，角色信息为空！");
        }
    }

    /**
     * @description deleteRole 删除角色
     * @param response
     * @param roleId
     *            角色
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("deleteRole.do")
    public void deleteRole(HttpServletResponse response, Long roleId) {
        logger.info("删除角色：" + roleId);
        if (roleId == null) {
            writeFailMsg(response, "删除角色失败，角色信息不能为空！");
        } else {
            try {
                roleService.doDeleteRole(roleId);
                writeSuccessMsg(response, "角色信息删除成功！");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                writeFailMsg(response, "角色信息删除失败！" + e.getMessage());
            }
        }
    }
}
