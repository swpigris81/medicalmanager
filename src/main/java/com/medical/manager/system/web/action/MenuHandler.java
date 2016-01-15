/**
 * 系统项目名称
 * com.supermarket.system.web.action
 * MenuHandler.java
 * 
 * 2015年7月3日-下午3:40:09
 * 2015版权所有
 * 
 */

package com.medical.manager.system.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.system.web.model.TblMenu;
import com.medical.manager.system.web.model.TblUser;
import com.medical.manager.system.web.service.IMenuService;

/**
 * @description
 * 
 * @time 2015年7月3日 下午3:40:09
 * @author Jason
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/menu")
public class MenuHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(MenuHandler.class);
    @Resource
    private IMenuService menuService;

    /**
     * @description index 菜单列表页面
     * @return String
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("index.do")
    public String index(HttpSession session, Model model) {
        String timeOut = logginTimeOut(session);
        if (timeOut != null) {
            return timeOut;
        }
        return "system/menu/index";
    }

    /**
     * @description menuList 查询菜单列表
     * @param session
     * @param model
     * @return String
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("menuList.do")
    public String menuList(HttpSession session, HttpServletResponse response) {
        String timeOut = logginTimeOut(session);
        if (timeOut != null) {
            return timeOut;
        }
        List menuList = menuService.findAllMenuListGrid();
        writeMsg(response, JSON.toJSONString(menuList, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty));
        return null;
    }

    /**
     * @description addMenu 增加菜单
     * @param response
     * @param model
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("addMenu.do")
    public void addMenu(HttpSession session, HttpServletResponse response, TblMenu menu) {
        TblUser user = (TblUser) session.getAttribute(SESSION_USER);
        if (menu != null) {
            logger.info("保存新增菜单：" + menu.toString());
            menu.setModifyDate(DateUtil.getNowDate());
            menu.setModifyTime(DateUtil.getNowTime());
            menu.setModifyOper(user.getUserCode());
            if (menu.getMenuId() != null) {
                menuService.saveOldMenu(menu);
            } else {
                menu.setCreateDate(DateUtil.getNowDate());
                menu.setCreateTime(DateUtil.getNowTime());
                menu.setCreateOper(user.getUserCode());
                menuService.saveNewMenu(menu);
            }
            writeSuccessMsg(response, "菜单保存成功！");
        } else {
            writeFailMsg(response, "菜单保存失败，未上传菜单信息！");
        }
    }

    /**
     * @description deleteMenu 删除菜单
     * @param session
     * @param response
     * @param menuId
     *            菜单编号
     * @return void
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("deleteMenu.do")
    public void deleteMenu(HttpServletResponse response, Long menuId) {
        logger.info("删除菜单：" + menuId);
        if (menuId == null) {
            writeFailMsg(response, "菜单删除失败，菜单编号为空！");
        }
        try {
            menuService.doDeleteMenu(menuId);
            writeSuccessMsg(response, "菜单删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            writeFailMsg(response, "菜单删除失败！" + e.getMessage());
        }
    }

    /**
     * @description queryChildMenu 查询子菜单
     * @param menuId
     *            主菜单编号
     * @param model
     *            返回数据
     * @return String
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    @RequestMapping("queryChildMenu.do")
    public String queryChildMenu(HttpSession session, HttpServletResponse response, Long menuId, Model model) {
        if (menuId == null || "".equals(model)) {
            return "system/menu/child";
        }
        TblUser user = (TblUser) session.getAttribute(SESSION_USER);
        List menuList = menuService.findChildMenuByMenuId(menuId, user.getUserCode());
        // model.addAttribute("childList", menuList);
        // return "menu/child";
        writeMsg(response, JSON.toJSONString(menuList));
        return null;
    }
}
