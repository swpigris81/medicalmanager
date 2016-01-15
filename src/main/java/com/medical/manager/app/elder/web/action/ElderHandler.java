/**
 * 系统项目名称
 * com.medical.manager.app.elder.web.action
 * ElderHandler.java
 * 
 * 2015年12月14日-下午3:44:43
 * 2015
 * 
 */

package com.medical.manager.app.elder.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.medical.manager.app.center.web.dao.ITblCusMedicalRecordsVisitDao;
import com.medical.manager.app.center.web.dao.TblMedicalRecordsDao;
import com.medical.manager.app.center.web.model.TblCusMedicalRecordsVisit;
import com.medical.manager.app.center.web.model.TblCustMedicalRecords;
import com.medical.manager.app.relatives.web.dao.ITblCusDeviceDao;
import com.medical.manager.app.relatives.web.dao.ITblPropaNewsDao;
import com.medical.manager.app.relatives.web.model.TblCusDevice;
import com.medical.manager.app.relatives.web.model.TblPropaNews;
import com.medical.manager.app.relatives.web.service.ITblCusRelationService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.dao.ICusBasicInfoDao;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.product.web.dao.ITblMachineCodeDao;
import com.medical.manager.product.web.dao.ITblMachineUseDao;
import com.medical.manager.product.web.model.TblMachineCode;
import com.medical.manager.product.web.model.TblMachineUse;

/**
 * 
 * ElderHandler 老人端接口
 * 
 * 2015年12月14日 下午3:44:43
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/app/elder")
public class ElderHandler extends BaseHandler {
	private Logger logger = Logger.getLogger(ElderHandler.class);

	@Resource
	private ITblPropaNewsDao tblPropaNewsDao;
	@Resource
	private ITblCusDeviceDao tblCusDeviceDao;
	@Resource
	private ICusBasicInfoDao cusBasicInfoDao;
	@Resource 
	private ITblMachineUseDao tblMachineUseDao;
	@Resource
	private ITblMachineCodeDao tblMachineCodeDao;
	@Resource
	private TblMedicalRecordsDao medicalRecordsDao;
	@Resource
	private ITblCusMedicalRecordsVisitDao tblCusMedicalRecordsVisitDao;
	
	@Resource
	private ITblCusRelationService tblCusRelationService;
	
    /**
     * 用户病历存放路径
     */
    @Value("${medical.records.location}")
    private String medicalRecordsLocation;
	
    /**
     * 病历上传
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param session
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("uploadMedicalRecords.do")
	public void uploadMedicalRecords(HttpServletResponse response,
			String mobileNo, @RequestParam MultipartFile recordsFile,
			HttpServletRequest request) {
        logger.info("上传病历");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
			if (StringUtil.isEmpty(mobileNo))
				throw new Exception("参数mobileNo不能为空！");
			if (recordsFile == null)
				throw new Exception("病历不能为空！");

			// 根据手机号码查询用户
			CusBasicInfo info = cusBasicInfoDao.queryByMobileNo(mobileNo);
			if (info == null)
				throw new Exception("根据手机号未查询到注册用户，请确认！");
            
            // 根据手机号码查询病历，无则新增
            Map<String, String> paramMap = new HashMap<String, String>(1);
            paramMap.put("mobileNo", mobileNo);
			List<TblCustMedicalRecords> records = medicalRecordsDao.findMedicalRecords(paramMap);
            if (records == null || records.size() == 0) {
            	TblCustMedicalRecords r = new TblCustMedicalRecords();
            	r.setCustId(info.getId());
            	r.setMobileNo(mobileNo);
            	r.setRecordNo(info.getId() + mobileNo);
            	medicalRecordsDao.insert(r);
            }
            
            // 保存病历图片
            final String sep = File.separator;
			StringBuffer uploadPath = new StringBuffer()
					.append(request.getSession().getServletContext().getRealPath(sep))
					.append(medicalRecordsLocation).append(sep).append(mobileNo).append(sep);
			File uploadDir = new File(uploadPath.toString());
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			uploadPath.append(recordsFile.getOriginalFilename());
			recordsFile.transferTo(new File(uploadPath.toString()));
            
            responseMap.put("success", true);
            responseMap.put("msg", "操作成功！");
        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("msg", "操作失败！" + e.getMessage());
        }
        writeMsg(response, responseMap);
    }
	
    /**
     * 新闻资讯存放图片路径
     */
    @Value("${propa.news.location}")
    private String propaNewsLocation;

	@RequestMapping("openDevice.do")
	public void openDevice(HttpServletResponse response, String deviceNo,
			String machineCode, String useId, String openId) {
		logger.info("ElderHandler.openDevice()设备开通...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {

			if (StringUtil.isEmpty(deviceNo) || StringUtil.isEmpty(machineCode)
					|| StringUtil.isEmpty(useId) || StringUtil.isEmpty(openId)) {
				throw new Exception("必填参数没空，请检查deviceNo,machineCode,useId和openId");
			}
			
			Long use_id = Long.valueOf(useId);
			Long open_id = Long.valueOf(openId);
			List<TblMachineCode> codes = tblMachineCodeDao.queryNotUsedByCode(machineCode);
			if (codes == null || codes.size() == 0) {
				throw new Exception("机器码不存在或已被使用，请重新输入！");
			}

			TblMachineCode code = codes.get(0);
			// 新增设备使用情况
			TblCusDevice device = new TblCusDevice(deviceNo, code.getId(),
					CommonEnums.YesOrNo.NO.id, CommonEnums.YesOrNo.NO.id,
					CommonEnums.YesOrNo.NO.id, use_id, open_id,
					DateUtil.fmtDateToYMDHMS(new Date()));
			// 更新机器码为已使用
			code.setUseStatus(CommonEnums.YesOrNo.YES.id);
			// 更新机器码使用记录
			TblMachineUse use = new TblMachineUse();
			CusBasicInfo info = cusBasicInfoDao.queryById(use_id);
			if (info == null) {
				throw new Exception("使用人不存在，请核实！");
			}
			use = new TblMachineUse(DateUtil.fmtDateToYMDHMS(new Date()), use_id,
					info.getRealName(), info.getPhone(), code.getId(), code.getCode(), null);

			tblCusDeviceDao.insert(device);
			tblMachineCodeDao.updateByPrimaryKey(code);
			tblMachineUseDao.insert(use);
			
			responseMap.put("success", true);
			responseMap.put("msg", "开通成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put("success", false);
			responseMap.put("msg", "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("queryOpenDeviceList.do")
	public void queryOpenDeviceList(HttpServletResponse response, String useId) {
		logger.info("ElderHandler.queryOpenDeviceList()查询设备开通列表...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(useId)) {
				throw new Exception("必填参数没空，请检查useId");
			}
			Long use_id = Long.valueOf(useId);
			List<TblCusDevice> devices = tblCusDeviceDao.queryOpenDeviceList(use_id);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, devices);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("queryDevicesByPhone.do")
	public void queryDevicesByPhone(HttpServletResponse response, String phone) {
		logger.info("ElderHandler.queryDevicesByPhone()根据电话查询设备...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(phone)) {
				throw new Exception("必填参数没空，请检查useId");
			}
			List<TblCusDevice> devices = tblCusDeviceDao.queryDevicesByPhone(phone);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, devices);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	/**
	 * 查询最新number条新闻
	 * @description 
	 * @param response
	 * @param number 数量，限整数，默认为10条
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	@RequestMapping("queryLatelyNumNews.do")
	public void queryLatelyNumNews(HttpServletRequest request,
			HttpServletResponse response, String number) {
		logger.info("ElderHandler.queryLatelyNumNews()查询最近num条资讯...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Integer num = NumberUtils.toInt(number, 10);
		
		try {
			List<TblPropaNews> news = tblPropaNewsDao.queryLatelyNumNews(num);
			for (TblPropaNews n : news) {
				Date date = DateUtil.fmtStrToDate(DateUtil.yyyy_MM_dd_HH_mm_ss, n.getPublish_time());
				String dateStr =  DateUtil.fmtDateToYMD(date);
				StringBuffer uploadPath = new StringBuffer();
				uploadPath.append(request.getSession().getServletContext().getRealPath(File.separator));
				uploadPath.append(propaNewsLocation);
				uploadPath.append(File.separator).append(dateStr);
				uploadPath.append(File.separator).append(n.getId());
				
				File uploadDir = new File(uploadPath.toString());
				if (uploadDir.exists()) {
					// 遍历文件夹，所有文件全部提取
					List<String> fileUrlList = new ArrayList<String>();
					if (uploadDir.isDirectory()) {
						String[] fileList = uploadDir.list();
						for (String fileName : fileList) {
							StringBuffer imgUrl = new StringBuffer();
							imgUrl.append(request.getScheme()).append("://");
							imgUrl.append(request.getServerPort()).append(request.getContextPath());
							imgUrl.append(propaNewsLocation);
							imgUrl.append(File.separator).append(dateStr);
							imgUrl.append(File.separator).append(n.getId());
							imgUrl.append(File.separator).append(fileName);
							fileUrlList.add(imgUrl.toString());
						}
					}
					n.setImg_urls(fileUrlList);
				}
			}
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, news);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	/**
	 * 同意别人申请添加我为他的关系人
	 * @description 
	 * @param response
	 * @param cusId			我的id
	 * @param relationId	添加我为关系人的他人Id
	 * @author Jason
	 * @since  1.0.0
	 */
	@RequestMapping("agreeRelationApply.do")
	public void agreeRelationApply(HttpServletResponse response, String cusId,
			String relationId) {
		logger.info("ElderHandler.agreeRelationApply()...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId) || StringUtil.isEmpty(relationId)) {
				throw new Exception("必填参数为空，请检查cusId/relationId");
			}
			tblCusRelationService.agreeRelationApply(cusId, relationId);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	/**
	 * 同意别人申请添加我为他的关系人
	 * @description 
	 * @param response
	 * @param cusId			我的id
	 * @param relationId	关系人id
	 * @author Jason
	 * @since  1.0.0
	 */
	@RequestMapping("allowVisitMe.do")
	public void allowVisitMe(HttpServletResponse response, String cusId,
			String relationId) {
		logger.info("ElderHandler.allowVisitMe()...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId) || StringUtil.isEmpty(relationId)) {
				throw new Exception("必填参数为空，请检查cusId/relationId");
			}
			tblCusRelationService.allowVisitMe(cusId, relationId);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	/**
	 * @description 分享病历给他人
	 * @param response 
	 * @param mobileNo	病历所属客户手机号码
	 * @param cusId		接收分享的客户id
	 * @author Jason
	 * @since  1.0.0
	 */
	@RequestMapping("shareMedicalRecords.do")
	public void shareMedicalRecords(HttpServletResponse response, String mobileNo,
			String cusId) {
		logger.info("ElderHandler.shareMedicalRecords()...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(mobileNo) || StringUtil.isEmpty(cusId)) {
				throw new Exception("必填参数为空，请检查mobileNo/cusId");
			}
			
			// query medicalRecords by mobileNo
			Map<String, String> paramMap = new HashMap<String, String>(1);
			paramMap.put("mobileNo", mobileNo);
			List<TblCustMedicalRecords> records = medicalRecordsDao.findMedicalRecords(paramMap );
			if (records == null || records.size() == 0) {
				throw new Exception("用户尚未上传病历!");
			}
			
			// save medicalRecordsVisit
			TblCusMedicalRecordsVisit recordsVisit = new TblCusMedicalRecordsVisit();
			for (TblCustMedicalRecords r : records) {
				recordsVisit.setRecord_id(r.getId());
				recordsVisit.setVisit_cus_id(Long.valueOf(cusId));
				tblCusMedicalRecordsVisitDao.insert(recordsVisit );
			}
			
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
}
