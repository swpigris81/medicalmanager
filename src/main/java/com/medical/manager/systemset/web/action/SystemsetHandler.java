/**
 * 
 */
package com.medical.manager.systemset.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.systemset.web.model.TblSysInfo;
import com.medical.manager.systemset.web.model.TblSysReceivables;
import com.medical.manager.systemset.web.query.TblSysInfoParam;
import com.medical.manager.systemset.web.service.ITblSysInfoService;
import com.medical.manager.systemset.web.service.ITblSysReceivablesService;

/**
 * @author jason
 *
 * 2015-12-4 上午11:54:57
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping("/systemset")
public class SystemsetHandler extends BaseHandler {
    
    private static Logger logger=Logger.getLogger(SystemsetHandler.class);
    
    @Resource
    private ITblSysReceivablesService tblSysReceivablesService;
    
    @Resource
    private ITblSysInfoService tblSysInfoService;
    
    /**
     * 跳转到账户设置主页
     * (这里描述这个方法适用条件 – 可选)
     * @param session
     * @return
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/sysReceivablesIndex.do")
    public String sysReceivablesIndex(HttpSession session,Model model){
        String timeout=logginTimeOut(session);
        if (timeout!=null) {
            return timeout;
        }
        TblSysReceivables tblSysReceivables=null;
        try {
            tblSysReceivables=this.tblSysReceivablesService.getSysReceivables();
        } catch (Exception e) {
            logger.error("获取收款账户设置信息失败");
        }
        model.addAttribute("tblSysReceivables", tblSysReceivables);
        return "systemset/sysReceivablesIndex";
        
    }
    
    /**
     * 保存和更新账户设置
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param sysReceivables
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/saveOrUpdateSysReceivables.do")
    public void saveOrUpdateSysReceivables(HttpServletResponse response,TblSysReceivables sysReceivables){
        if (sysReceivables.getId()==null) {
            logger.info("保存收款账户设置");
            try {
                this.tblSysReceivablesService.saveSysReceivables(sysReceivables);
                writeSuccessMsg(response, "保存成功");
            } catch (Exception e) {
                logger.error("保存收款账户设置失败", e);
                writeFailMsg(response, "保存收款账户设置失败");
            }
        }else {
            logger.info("更新收款账户设置");
            try {
                this.tblSysReceivablesService.updateSysReceivables(sysReceivables);
                writeSuccessMsg(response, "更新收款账户成功");
            } catch (Exception e) {
                logger.error("更新收款账户设置失败", e);
                writeFailMsg(response, "更新收款账户设置失败");
            }
            
        }
    }
    
    /**
     * 跳转到系统设置主页
     * (这里描述这个方法适用条件 – 可选)
     * @param session
     * @param model
     * @return
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/sysInfoIndex.do")
    public String sysInfoIndex(HttpSession session){
        String timeout=logginTimeOut(session);
        if (timeout!=null) {
            return timeout;
        }
        return "systemset/sysInfoIndex";
    }
    
    /**
     * 初始化数据
     * @param response
     */
    @RequestMapping("/initSysInfo.do")
    public void initSysInfo(HttpServletResponse response){
    	List<TblSysInfo> sysInfos=null;
        try {
            sysInfos=this.tblSysInfoService.getSysInfo();
            writeMsg(response, JSON.toJSONString(sysInfos));
        } catch (Exception e) {
            logger.error("获取系统设置信息失败!", e);
            writeFailMsg(response, "获取系统设置信息失败!");
        }
    }
    
    /**
     * 保存或更新数据
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param dataMap
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/saveOrUpdateSysInfo.do")
    public void saveOrUpdateSysInfo(HttpServletResponse response,TblSysInfoParam sysInfoParam){
        logger.info("保存系统设置信息");
        try {
            this.tblSysInfoService.saveOrUpdateSysInfo(sysInfoParam);
            writeSuccessMsg(response, "保存成功");
        } catch (Exception e) {
            logger.error("保存失败", e);
            writeFailMsg(response, "保存失败");
        }
    }
    
}
