
/**
 * 系统项目名称
 * com.medical.manager.common.listener
 * SessionListener.java
 * 
 * 2015年12月3日-上午9:17:00
 * 2015
 * 
 */
 
package com.medical.manager.common.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.system.web.model.TblUser;
import com.medical.manager.system.web.service.IUserService;


/**
 * 
 * SessionListener 系统会话
 * 
 * 2015年12月3日 上午9:17:00
 * 
 * @version 1.0.0
 * 
 */
public class SessionListener implements HttpSessionListener, ServletRequestListener {
    private Logger logger = Logger.getLogger(SessionListener.class);
    private IUserService userService;
    private HttpServletRequest request;
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
     */
    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        logger.info("Session Created。");
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        logger.info("Session Destroyed");
        HttpSession session = event.getSession();
        TblUser user = (TblUser) session.getAttribute(BaseHandler.SESSION_USER);
        if(user != null){
            String ip = "";
            if (request.getHeader("x-forwarded-for") == null) { 
                ip = request.getRemoteAddr(); 
            }else{
                ip = request.getHeader("x-forwarded-for");
            }
            String loginTime = DateUtil.getNowTimes();
            user.setLastLoginIp(ip);
            user.setLastLoginTime(loginTime);
            if(userService == null){
                ApplicationContext ctx = WebApplicationContextUtils.
                        getRequiredWebApplicationContext(session.getServletContext());
                userService = (IUserService) ctx.getBean("userService");
            }
            userService.doUpdateUserLastLogin(user);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        request = (HttpServletRequest)event.getServletRequest();
    }
}
