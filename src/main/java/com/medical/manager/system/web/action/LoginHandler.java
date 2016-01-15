
/**
 * 系统项目名称
 * com.supermarket.system.web.action
 * LoginHandler.java
 * 
 * 2015年7月2日-上午9:41:27
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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.system.web.model.TblMenu;
import com.medical.manager.system.web.model.TblUser;
import com.medical.manager.system.web.service.IUserService;


/**
 * @description 用户登录登出操作
 * 
 * @time 2015年7月2日 上午9:41:27
 * @author Jason
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(LoginHandler.class);
    @Resource
    private IUserService userService;
    
    @RequestMapping("doLogin.do")
    public String doLogin(HttpServletRequest request,HttpServletResponse response){
        logger.info("doLogin...");
        String userCode = request.getParameter("userCode");
        String password = request.getParameter("passWord");
        logger.info("user: " + userCode + " is login now .");
        Map responseMap = null;
        try{
            responseMap = userService.doLogin(userCode, password);
            Boolean bool = (Boolean) responseMap.get("success");
            if(bool){
                logger.info("user login success .");
                TblUser user = (TblUser) responseMap.get("user");
                List<TblMenu> menuList = (List<TblMenu>) responseMap.get("menuList");
                request.getSession().setAttribute(SESSION_USER, user);
                request.getSession().setAttribute(SESSION_USER_MENU_LIST, menuList);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseMap = new HashMap();
            responseMap.put("success", false);
            responseMap.put("msg", "系统异常！" + e.getMessage());
        }
        writeMsg(response, responseMap);
        return null;
    }
    /**
     * @description doLogout 用户登出，跳转到系统首页
     * @param request
     * @param response
     * @return String
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    @RequestMapping("doLogout.do")
    public String doLogout(HttpServletRequest request,HttpServletResponse response){
        //销毁Session
        request.getSession().invalidate();
        logger.info("logout success ");
        return "redirect:/index.jsp";
    }
}
