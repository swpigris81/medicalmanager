
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.action
 * BlacklistHandler.java
 * 
 * 2015年12月4日-下午2:26:22
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.medical.manager.propaganda.web.model.TblPropaBlacklist;
import com.medical.manager.propaganda.web.model.TblPropaSiteMessage;
import com.medical.manager.propaganda.web.service.IBlacklistService;
import com.medical.manager.system.web.model.TblUser;


/**
 * 
 * BlacklistHandler 黑名单
 * 
 * 2015年12月4日 下午2:26:22
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/blacklist")
public class BlacklistHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(MessageHandler.class);
    @Resource
    private IBlacklistService blacklistService;
    /**
     * index 跳转黑名单首页
     * (这里描述这个方法适用条件 – 可选)
     * @return String 首页
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/index.do")
    public String index(HttpSession session, Model model){
        logger.info("黑名单首页");
        String timeOut = logginTimeOut(session);
        if(timeOut != null){
            return timeOut;
        }
        return "propaganda/blacklist/index";
    }
    
    /**
     * blackList 查询黑名单列表
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param appSlide 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/blackList.do")
    public void blackList(HttpServletResponse response, TblPropaBlacklist blacklist,
            Integer page, Integer rows){
        logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
        Map paramMap = new HashMap();
        if(blacklist != null){
            try {
                paramMap = BeanUtils.describe(blacklist);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        List<TblPropaBlacklist> userList = blacklistService.queryWithPaging(page, rows, paramMap);
        writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblPropaBlacklist>) userList)));
    }
    /**
     * <p>Discription:[保存黑名单]</p>
     * @param response
     * @param session
     * @param siteMessage 黑名单
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    @RequestMapping("/saveBlacklist.do")
    public void saveBlacklist(HttpServletResponse response, HttpSession session,
            TblPropaBlacklist blacklist){
        logger.info("保存黑名单");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        TblUser user = getSessionUser(session);
        boolean success = false;
        String msg = "";
        try{
            if(blacklist == null){
                throw new Exception("黑名单信息为空！");
            }
            if(blacklist.getId() == null){
                //新增
                blacklist.setCreateDate(DateUtil.getNowDate());
                blacklist.setCreateOper(user.getUserCode());
                blacklist.setCreateTime(DateUtil.getNowTime());
                blacklistService.doSaveNewBlacklist(blacklist);
            }else{
                //修改
                blacklistService.doUpdateBlacklist(blacklist);
            }
            success = true;
            msg = "黑名单信息保存成功！";
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            msg = "保存黑名单信息失败！" + e.getMessage();
        }
        resultMap.put("success", success);
        resultMap.put("msg", msg);
        writeMsg(response, resultMap);
    }
    /**
     * <p>Discription:[删除黑名单]</p>
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    @RequestMapping("/deleteBlacklist.do")
    public void deleteBlacklist(HttpServletResponse response,
            String blackIds){
        logger.info("删除黑名单");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean success = false;
        String msg = "";
        try{
            List<Long> blackIdList = new ArrayList<Long>();
            String [] blackIdArray = blackIds.split(",");
            for(String blackId : blackIdArray){
                if(!StringUtils.isEmpty(blackId)){
                    blackIdList.add(NumberUtils.toLong(blackId));
                }
            }
            //删除幻灯片并且删除对应的文件
            blacklistService.doDeleteBlacklist(blackIdList);
            success = true;
            msg = "删除黑名单信息成功！";
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            msg = "删除黑名单信息失败！" + e.getMessage();
        }
        resultMap.put("success", success);
        resultMap.put("msg", msg);
        writeMsg(response, resultMap);
    }
}
