
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.action
 * AppSlideHandler.java
 * 
 * 2015年12月4日-下午2:23:52
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.propaganda.web.model.TblPropaAppSlide;
import com.medical.manager.propaganda.web.service.IAppSlideService;
import com.medical.manager.system.web.model.TblUser;


/**
 * 
 * AppSlideHandler
 * 
 * 2015年12月4日 下午2:23:52
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/appindex")
public class AppSlideHandler extends BaseHandler {
    private Logger logger = Logger.getLogger(AppSlideHandler.class);
    @Value("${appslide.upload.image}")
    private String slideImgLocation;
    @Resource
    private IAppSlideService appSlideService;
    /**
     * index 跳转APP幻灯片首页
     * (这里描述这个方法适用条件 – 可选)
     * @return String 首页
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/index.do")
    public String index(HttpSession session, Model model){
        logger.info("APP幻灯片首页");
        String timeOut = logginTimeOut(session);
        if(timeOut != null){
            return timeOut;
        }
        return "propaganda/appindex/index";
    }
    
    /**
     * slideList 查询幻灯片列表
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param appSlide 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/slideList.do")
    public void slideList(HttpServletResponse response, TblPropaAppSlide appSlide,
            Integer page, Integer rows){
        logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
        Map paramMap = new HashMap();
        if(appSlide != null){
            try {
                paramMap = BeanUtils.describe(appSlide);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        List<TblPropaAppSlide> userList = appSlideService.queryWithPaging(page, rows, paramMap);
        writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblPropaAppSlide>) userList)));
    }
    /**
     * uploadImg 上传图片
     * (这里描述这个方法适用条件 – 可选)
     * @param response 
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/uploadImg.do")
    public void uploadImg(HttpServletResponse response, 
            HttpServletRequest request, MultipartFile image){
        logger.info("上传幻灯片");
        Map<String, Object> resuleMap = new HashMap<String, Object>();
        boolean success = false;
        String resultMsg = "";
        try{
            if(image == null || image.isEmpty()){
                logger.info("未上传图片，不保存图片信息。");
                success = false;
                resultMsg = "未上传图片！";
            }else{
                String fileName=image.getOriginalFilename();
                String extName = fileName.substring(fileName.lastIndexOf("."));
                String uploadPath = request.getSession().getServletContext().getRealPath("/");
                uploadPath += slideImgLocation + "/" + DateUtil.getNowDate() + "/";
                File uploadDir=new File(uploadPath);
                if(!uploadDir.exists()){
                    uploadDir.mkdirs();
                }
                String uploadFilePath = uploadPath + DateUtil.getTime() + "" + extName;
                File uploadFile=new File(uploadFilePath);
                image.transferTo(uploadFile);//上传
                String imgUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "" + request.getContextPath() + 
                        slideImgLocation + "/" + DateUtil.getNowDate() + "/" + uploadFile.getName();
                logger.info("上传文件存储地址：" + uploadFile.getPath());
                logger.info("上传文件访问地址：" + imgUrl);
                resuleMap.put("imageUrl", imgUrl);
                resuleMap.put("imageLocation", uploadFile.getPath());
                success = true;
            }
        }catch(Exception e){
            logger.error("图片上传失败！" + e.getMessage(), e);
            resultMsg = "图片上传失败！" + e.getMessage();
        }
        resuleMap.put("success", success);
        resuleMap.put("msg", resultMsg);
        writeMsg(response, resuleMap);
    }
    /**
     * saveSlide 保存幻灯片
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param appSlide 
     * void
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/saveSlide.do")
    public void saveSlide(HttpServletResponse response, HttpSession session,
            TblPropaAppSlide appSlide){
        logger.info("保存幻灯片");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        TblUser user = getSessionUser(session);
        boolean success = false;
        String msg = "";
        try{
            if(appSlide == null){
                throw new Exception("幻灯片信息为空！");
            }
            if(appSlide.getId() == null){
                //新增
                appSlide.setCreateDate(DateUtil.getNowDate());
                appSlide.setCreateOper(user.getUserCode());
                appSlide.setCreateTime(DateUtil.getNowTime());
                appSlideService.doSaveNewAppSlide(appSlide);
            }else{
                //修改
                appSlideService.doUpdateAppSlide(appSlide);
            }
            success = true;
            msg = "幻灯片信息保存成功！";
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            msg = "保存幻灯片信息失败！" + e.getMessage();
        }
        resultMap.put("success", success);
        resultMap.put("msg", msg);
        writeMsg(response, resultMap);
    }
    /**
     * <p>Discription:[删除幻灯片]</p>
     * @param response
     * @param appSlide
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    @RequestMapping("/deleteSlide.do")
    public void deleteSlide(HttpServletResponse response,
            String slideIds){
        logger.info("删除幻灯片：" + slideIds);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean success = false;
        String msg = "";
        try{
            List<Long> slideIdList = new ArrayList<Long>();
            String [] slideIdArray = slideIds.split(",");
            for(String slideId : slideIdArray){
                if(!StringUtils.isEmpty(slideId)){
                    slideIdList.add(NumberUtils.toLong(slideId));
                }
            }
            //删除幻灯片并且删除对应的文件
            appSlideService.doDeleteAppSlide(slideIdList);
            success = true;
            msg = "删除幻灯片信息成功！";
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            msg = "删除幻灯片信息失败！" + e.getMessage();
        }
        resultMap.put("success", success);
        resultMap.put("msg", msg);
        writeMsg(response, resultMap);
    }
}
