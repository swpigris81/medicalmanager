
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.action
 * MessageHandler.java
 * 
 * 2015年12月4日-下午1:00:19
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.propaganda.web.model.TblPropaSiteMessage;
import com.medical.manager.propaganda.web.service.IMessageService;
import com.medical.manager.system.web.model.TblSysProvince;
import com.medical.manager.system.web.model.TblUser;
import com.medical.manager.system.web.service.ISysAdministrativeService;


/**
 * 
 * MessageHandler 站内信
 * 
 * 2015年12月4日 下午1:00:19
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/messaging")
public class MessageHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(MessageHandler.class);
    @Resource
    private IMessageService messageService;
    @Resource
    private ISysAdministrativeService sysAdministrativeService;
    /**
     * index 跳转站内信首页
     * (这里描述这个方法适用条件 – 可选)
     * @return String 首页
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/index.do")
    public String index(HttpSession session, Model model){
        logger.info("站内信首页");
        String timeOut = logginTimeOut(session);
        if(timeOut != null){
            return timeOut;
        }
        TblUser user = getSessionUser(session);
        List<TblSysProvince> provinceList = sysAdministrativeService.findAllProvince();
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("user", user);
        return "propaganda/messaging/index";
    }
    /**
     * messageList 查询站内信列表
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param appSlide 
     * @exception 
     * @since  1.0.0
     */
     @RequestMapping("/messageList.do")
    public void messageList(HttpServletResponse response, HttpSession session,
            TblPropaSiteMessage siteMessage,
            String beginDate, String endDate,
            Integer page, Integer rows){
        logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
        TblUser user = getSessionUser(session);
        Map paramMap = new HashMap();
        if(siteMessage != null){
            try {
                siteMessage.setProvince(NumberUtils.toInt(user.getUserProvince(), 0));
                siteMessage.setCity(NumberUtils.toInt(user.getUserCity(), 0));
                paramMap = BeanUtils.describe(siteMessage);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        
        paramMap.put("beginDate", beginDate);
        paramMap.put("endDate", endDate);
        List<TblPropaSiteMessage> userList = messageService.queryWithPaging(page, rows, paramMap);
        writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblPropaSiteMessage>) userList)));
    }
    /**
     * <p>Discription:[发送站内信]</p>
     * @param response
     * @param siteMessage
     * @param session
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    @RequestMapping("/sendMessage.do")
    public void sendMessage(HttpServletResponse response, TblPropaSiteMessage siteMessage,
            HttpSession session, String validCode){
        logger.info("保存站内信");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        TblUser user = getSessionUser(session);
        boolean success = false;
        String msg = "";
        try{
            if(siteMessage == null){
                throw new Exception("站内信息为空！");
            }
            if(!session.getAttribute(IMAGE_VERIFY_CODE).toString().equalsIgnoreCase(validCode)){
                throw new Exception("验证码错误！");
            }
            if(siteMessage.getId() == null){
                //发送站内信
                siteMessage.setCreateDate(DateUtil.getNowDate());
                siteMessage.setCreateOper(user.getUserCode());
                siteMessage.setCreateTime(DateUtil.getNowTime());
                siteMessage.setStatus("00");
                messageService.doSendMessage(siteMessage);
                msg = "站内信息已发送成功！";
            }else{
                //修改站内信
                messageService.doUpdateMessage(siteMessage);
                msg = "站内信息已修改成功！";
            }
            success = true;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            msg = "站内信息保存失败！" + e.getMessage();
        }
        resultMap.put("success", success);
        resultMap.put("msg", msg);
        writeMsg(response, resultMap);
    }
    /**
     * <p>Discription:[阅读站内信，更新状态]</p>
     * @param response
     * @param siteMessage
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    @RequestMapping("/readMessage.do")
    public void readMessage(HttpServletResponse response, TblPropaSiteMessage siteMessage){
        logger.info("阅读站内信");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean success = false;
        String msg = "";
        try{
            if(siteMessage == null || siteMessage.getId() == null){
                throw new Exception("站内信息为空！");
            }
            siteMessage.setStatus("01");
            messageService.doReadMessage(siteMessage);
            success = true;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            msg = "站内信息保存失败！" + e.getMessage();
        }
        resultMap.put("success", success);
        resultMap.put("msg", msg);
        writeMsg(response, resultMap);
    }
}
