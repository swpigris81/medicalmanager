
/**
 * 系统项目名称
 * com.supermarket.common.filter
 * SessionFilter.java
 * 
 * 2015年7月2日-上午10:30:57
 * 2015版权所有
 * 
 */
 
package com.medical.manager.common.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.system.web.model.TblMenu;
import com.medical.manager.system.web.model.TblUser;



/**
 * @description 
 * 
 * @time 2015年7月2日 上午10:30:57
 * @author Jason
 * @version 1.0.0
 * 
 */

public class SessionFilter implements Filter {
    private static final Logger logger = Logger.getLogger(SessionFilter.class);
    
    private String ignoreSession;
    private static final String INDEX_PAGE = "/index.jsp";
    private static final String LOGINED_PAGE = "/main/index.do";
    private static final String WECHAT_PAGE = "/wechat/";
    private static final String APP_PAGE = "/app/";
    private static final String JS_UEDITOR = "/js/ueditor/";
    private WebApplicationContext context;
    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest _request = (HttpServletRequest) request;
        HttpServletResponse _response = (HttpServletResponse) response;

        _response.setContentType("text/xml; charset=utf-8");
        _response.setCharacterEncoding("utf-8");
        
        String contextPath = _request.getContextPath();
        TblUser user = (TblUser) _request.getSession().getAttribute("SESSION_USER");
        List<TblMenu> menuList = (List<TblMenu>) _request.getSession().getAttribute(BaseHandler.SESSION_USER_MENU_LIST);
        String currentRequestUrl = _request.getRequestURI();
        logger.info("用户请求URL：" + currentRequestUrl);
        //前段访问类型
        String requestType = _request.getHeader("X-Requested-With");
        String responseUrl = handlerFilter(request, response, chain, user, menuList, contextPath, currentRequestUrl);
        if(requestType != null && "XMLHttpRequest".equals(requestType)){
            //前端异步请求
            if(responseUrl != null && !"".equals(responseUrl)){
                //前端异步请求无权限
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("success", false);
                resultMap.put("msg", "你没有权限访问当前请求！");
                _response.getWriter().print(JSON.toJSONString(resultMap));
            }
        }else{
            if(responseUrl != null && !"".equals(responseUrl)){
                _response.sendRedirect(responseUrl);
            }
        }
    }
    /**
     * @description handlerFilter 过滤请求
     * @param request 请求
     * @param response 响应
     * @param chain 处理
     * @param user 回话用户
     * @param contextPath 请求根路径
     * @param currentRequestUrl 当前请求路径
     * @return 如果返回null，则说明有权限访问当前请求路径，如果不是null，则说明没有权限访问当前路径，需要跳转到返回的值指向的路径中。
     * @throws IOException
     * @throws ServletException 
     * @return String
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public String handlerFilter(ServletRequest request, ServletResponse response, FilterChain chain, TblUser user, 
            List<TblMenu> menuList, String contextPath, String currentRequestUrl) throws IOException, ServletException{
        if(currentRequestUrl.indexOf(contextPath) >= 0){
            currentRequestUrl = currentRequestUrl.substring(contextPath.length());
        }
        //logger.info("用户请求URL：" + currentRequestUrl);
        if(currentRequestUrl.indexOf(INDEX_PAGE) >= 0){
            logger.info("用户请求首页，无条件跳转");
            chain.doFilter(request, response);
            return null;
        }
        logger.info("查询忽略的菜单信息。" + ignoreSession);
        if(ignoreSession == null || "".equals(ignoreSession.trim())){
            //没有忽略菜单，则默认进入首页（不管请求来自哪里）
            logger.info("不存在忽略列表，跳转首页。");
            //_response.sendRedirect(contextPath + INDEX_PAGE);
            return contextPath + INDEX_PAGE;
        }
        //增加：如果访问的是微信菜单，无条件放行
        if(currentRequestUrl.startsWith(WECHAT_PAGE)){
            logger.info("用户请求微信服务，无条件跳转");
            chain.doFilter(request, response);
            return null;
        }
        if(currentRequestUrl.startsWith(APP_PAGE)){
            logger.info("APP用户请求服务，无条件跳转");
            chain.doFilter(request, response);
            return null;
        }
        
        if(currentRequestUrl.startsWith(JS_UEDITOR)){
            logger.info("富文本请求，无条件跳转");
            chain.doFilter(request, response);
            return null;
        }
        
        if(Arrays.asList(ignoreSession.split(",")).contains(currentRequestUrl)){
            //请求在忽略菜单中
            logger.info("请求在忽略列表中。");
            chain.doFilter(request, response);
            return null;
        }else{
//            logger.info("请求不在忽略列表中，检查用户是否登录。");
            if(user != null){
//                logger.info("用户已登录，检查是否访问首页.");
                if(LOGINED_PAGE.equals(currentRequestUrl)){
                    logger.info("用户访问登录后的首页，无条件放行。");
                    chain.doFilter(request, response);
                    return null;
                }
//                logger.info("用户已登录，查询有权限的菜单信息。");
//                IMenuService menuService = (IMenuService) context.getBean("menuService");
                //List<TblMenu> menuList = menuService.findByUserCode(user.getUserCode());
                
//                List menuList = menuService.findByUserCodeAndMenu(user.getUserCode(), currentRequestUrl);
                if(menuList == null || menuList.isEmpty()){
                    logger.info("用户没有权限菜单，访问拒绝！");
                    //_response.sendRedirect(contextPath + LOGINED_PAGE);
                    return contextPath + LOGINED_PAGE;
                }
                for(TblMenu menu : menuList){
                    if(menu.getMenuUrl() != null && menu.getMenuUrl().indexOf(currentRequestUrl) >= 0){
                        logger.info("用户有权限访问菜单，跳转。");
                        chain.doFilter(request, response);
                        return null;
                    }
                }
                logger.info("用户无权限访问菜单，访问拒绝。");
                //_response.sendRedirect(contextPath + LOGINED_PAGE);
                return contextPath + LOGINED_PAGE;
            }
            logger.info("用户未登录，且请求不在忽略列表中，跳转首页。");
            //_response.sendRedirect(contextPath + INDEX_PAGE);
            return contextPath + INDEX_PAGE;
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Session fileter init...");
        context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        setIgnoreSession(filterConfig.getInitParameter("ignoreSession"));
    }

    public void setIgnoreSession(String ignoreSession) {
        this.ignoreSession = ignoreSession;
    }

}
