
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.action
 * RelativesHandler.java
 * 
 * 2015年12月14日-下午3:45:44
 * 2015
 * 
 */

package com.medical.manager.app.relatives.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medical.manager.app.relatives.web.dao.ITblCusRelativesDao;
import com.medical.manager.app.relatives.web.model.TblCusAddress;
import com.medical.manager.app.relatives.web.model.TblCusRelatives;
import com.medical.manager.app.relatives.web.service.ITblCusAddressService;
import com.medical.manager.app.relatives.web.service.ITblHelpRecordService;
import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.common.util.StringUtil;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.customer.web.dao.ITblCusReportDao;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.customer.web.model.TblCusReport;
import com.medical.manager.customer.web.service.ICusBasicInfoService;
import com.medical.manager.product.web.dao.ITblDeliverProductDao;
import com.medical.manager.product.web.dao.ITblProductDao;
import com.medical.manager.product.web.model.TblBrand;
import com.medical.manager.product.web.model.TblDeliverProduct;
import com.medical.manager.product.web.model.TblProduct;
import com.medical.manager.systemset.web.dao.ITblSysInfoDao;
import com.medical.manager.systemset.web.model.TblSysInfo;

/**
 * 
 * RelativesHandler 亲属端接口
 * 
 * 2015年12月14日 下午3:45:44
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/app/relatives")
public class RelativesHandler extends BaseHandler {
	private Logger logger = Logger.getLogger(RelativesHandler.class);

	@Resource
	private ITblProductDao tblProductDao;
	@Resource
	private ITblSysInfoDao tblSysInfoDao;
	@Resource
	private ITblCusReportDao tblCusReportDao;
	@Resource
	private ITblCusRelativesDao tblCusRelativesDao;
	@Resource
	private ITblDeliverProductDao tblDeliverProductDao; 

	@Resource
	private ICusBasicInfoService cusBasicInfoService;
	@Resource
	private ITblCusAddressService tblCusAddressService;
	@Resource
	private ITblHelpRecordService tblHelpRecordService;
	
	
	@RequestMapping("relativesIndex.do")
	public void relativesIndex(HttpServletResponse response, String cusId) {
		logger.info("RelativesHandler.relativesIndex()...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId)) {
				throw new Exception("cusId不能为空！");
			}
			Map info = cusBasicInfoService.queryRelativesIndex(Long.parseLong(cusId));
			if (info == null) {
				info = new HashMap();
			}
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, info);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	/**
	 * 这里是方法描述 评价
	 *
	 * @date 2015年12月18日 下午4:00:16
	 * @param response
	 * @param helpId	求救记录id
	 * @param evaluationType 评价类型：0医院或120中心，1急救车
	 * void
	 */
	@RequestMapping("evaluationHelp.do")
	public void evaluationHelp(HttpServletResponse response, String helpId,
			String cusId, String evaluationType, String evaluateContent,
			String evaluateStar) {
		logger.info("RelativesHandler.evaluationHelp()...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(helpId)
					|| StringUtil.isEmpty(cusId)
					|| StringUtil.isEmpty(evaluationType)
					|| StringUtil.isEmpty(evaluateContent)
					|| StringUtil.isEmpty(evaluateStar)) {
				throw new Exception("helpId/cusId/evaluationType/evaluateContent/evaluateStar都不能为空");
			}
			tblHelpRecordService.evaluationHelp(Long.valueOf(helpId), cusId,
					evaluationType, evaluateContent, evaluateStar);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id,
					"操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("queryHelpRecord.do")
	public void queryHelpRecord(HttpServletResponse response, String cusId,
			String phone) {
		logger.info("RelativesHandler.queryHelpRecord()...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId) && StringUtil.isEmpty(phone)) {
				throw new Exception("cusId和phone不能同时为空");
			}
			Map<String, String> paramMap = new HashMap<String, String>(2);
			paramMap.put("cus_id", cusId);
			paramMap.put("cus_phone", phone);
			List<Map<String, String>> data = tblHelpRecordService.queryByMap(paramMap);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, data);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id,
					"操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("queryInvitationInfo.do")
	public void queryInvitationInfo(HttpServletResponse response, String cusId) {
		logger.info("RelativesHandler.queryInvitationInfo()...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId)) {
				throw new Exception("必填字段为空，请检查cusId");
			}
			TblSysInfo sysInfo = tblSysInfoDao.queryByCusId(Long.parseLong(cusId));
			CusBasicInfo basicInfo = cusBasicInfoService.queryById(cusId);
			if (basicInfo == null) {
				responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
				responseMap.put(CommonEnums.ReturnKey.MSG.id, "用户不存在");
			} else {
				Map<String, String> data = new HashMap<String, String>(2);
				data.put("invitationCode", basicInfo.getInvitationCode());
				data.put("paramValue", sysInfo.getParamValue());
				responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
				responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作成功");
				responseMap.put(CommonEnums.ReturnKey.DATA.id, data);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("report.do")
	public void report(HttpServletResponse response, String cusId, String reported, String reportInfo, String suggest) {
		logger.info("RelativesHandler.report()举报违规...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			// 根据被举报人查询被举报人信息 00省级 01市级 02县级
			CusBasicInfo info = cusBasicInfoService.queryById(cusId);
			if (info == null) {
				responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
				responseMap.put(CommonEnums.ReturnKey.MSG.id, "举报单位不存在");
			} else {
				TblCusReport report = new TblCusReport();
				if (CommonEnums.CustomType.A120.id.equals(info.getCusType())) {
					TblCusBasicExpand expand = info.getExpand();
					if (expand == null || StringUtil.isEmpty(expand.getTollLevel())) {
						throw new Exception("举报单位不存在");
					} else if ("00".equals(expand.getTollLevel())){
						report.setReportType(CommonEnums.ReportType.PROVINCE120.id);
					} else if ("01".equals(expand.getTollLevel())) {
						report.setReportType(CommonEnums.ReportType.CITY120.id);
					} else if ("03".equals(expand.getTollLevel())) {
						report.setReportType(CommonEnums.ReportType.COUNTY120.id);
					}
				} else if (CommonEnums.CustomType.OLD_PERSON.id.equals(info.getCusType())
						|| CommonEnums.CustomType.RELITIVE.id.equals(info.getCusType())) {
					report.setReportType(CommonEnums.ReportType.COMMON.id);
				} else if (CommonEnums.CustomType.AMBULANCE.id.equals(info.getCusType())) {
					report.setReportType(CommonEnums.ReportType.AMBULANCE.id);
				} else {
					report.setReportType(CommonEnums.ReportType.OTHERS.id);
				}
				report.setInformants(cusId);
				report.setReported(reported);
				report.setReportInfo(reportInfo);
				report.setRemark(suggest);
				report.setReportTime(DateUtil.fmtDateToYMDHMS(new Date()));
				tblCusReportDao.insert(report);
				responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
				responseMap.put(CommonEnums.ReturnKey.MSG.id, "举报成功");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("queryRelativesDetail.do")
	public void queryRelativesDetail(HttpServletResponse response, String relativesId) {
		logger.info("RelativesHandler.queryRelativesDetail()...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			TblCusRelatives relatives = tblCusRelativesDao.queryRelativesDetail(Long.valueOf(relativesId));
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, relatives);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}

	@RequestMapping("queryRelativesList.do")
	public void queryRelativesList(HttpServletResponse response, String cusId) {
		logger.info("RelativesHandler.queryRelativesList()...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId)) {
				throw new Exception("cusId不能为空");
			}
			List<TblCusRelatives> relativeses = tblCusRelativesDao.queryRelativesList(Long.valueOf(cusId));
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, relativeses);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("queryProductList.do")
	public void queryProductList(HttpServletResponse response) {
		logger.info("RelativesHandler.queryProductList()查询商品列表...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			List<TblProduct> products = tblProductDao.queryAllProduct();
			List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>(products.size());
			Map<String, Object> data = null;
			for (TblProduct p : products) {
				data = new HashMap<String, Object>();
				data.put("productId", p.getProductId());// 商品ID
				data.put("productNo", p.getProductNo());// 货号
				data.put("inventoryNum", p.getInventoryNum());// 库存数量
				data.put("loadCapacity", p.getLoadCapacity());// 承载重量
				data.put("freight", p.getTblBrand().getNo().toString());// 运费
				data.put("isInvoice", p.getTblBrand().getBrandName());// 是否有发票，Y有，N无
				data.put("discountPrice", p.getDiscountPrice());// 优惠价格
				data.put("marketPrice", p.getMarketPrice());// 市场价格
				data.put("isMinusInventory", p.getIsMinusInventory());// 是否减库存，Y减，N不减
				data.put("addTime", p.getAddTime());// 添加时间
				data.put("introduction", p.getIntroduction());// 商品介绍
//				data.put("productIcon", p.getBrandImg());// 商品图标
				if (p.getTblBrand() != null) {
					data.put("brandNo", p.getTblBrand().getNo());// 品牌ID
					data.put("brandName", p.getTblBrand().getBrandName());// 品牌名称
				}
				data.put("productImgs", StringUtil.parseHtml(p.getBrandImg()));// 商品图片集合
				dataList.add(data);
			}
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, dataList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("queryDeliverList.do")
	public void queryDeliverList(HttpServletResponse response, String cusId) {
		logger.info("RelativesHandler.queryDeliverList()查询物流列表...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId)) {
				throw new Exception("cusId不能为空");
			}
			List<TblDeliverProduct> delivers = tblDeliverProductDao.queryListByCusId(Long.valueOf(cusId));
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, delivers);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("queryAddressList.do")
	public void queryAddressList(HttpServletResponse response, String cusId) {
		logger.info("RelativesHandler.queryAddressList()查询收货地址列表...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId)) {
				throw new Exception("cusId不能为空");
			}
			List<TblCusAddress> addresses = tblCusAddressService.queryByCusId(Long.valueOf(cusId));
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询成功");
			responseMap.put(CommonEnums.ReturnKey.DATA.id, addresses);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "查询失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("updateAddress.do")
	public void updateAddress(HttpServletResponse response, String addresId,
			String receiver, String phone, String zipCode, String address,
			String defaultFlag, String remark) {
		logger.info("RelativesHandler.updateAddress()修改收货地址...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(addresId)) {
				throw new Exception("addresId不能为空");
			}
			TblCusAddress cusAddress = new TblCusAddress(null, receiver, phone,
					zipCode, address, defaultFlag);
			cusAddress.setId(Long.valueOf(addresId));
			tblCusAddressService.update(cusAddress);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "操作失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("insertAddress.do")
	public void insertAddress(HttpServletResponse response, String cusId,
			String receiver, String phone, String zipCode, String address,
			String defaultFlag, String remark) {
		logger.info("RelativesHandler.insertAddress()新增收货地址...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(cusId) || StringUtil.isEmpty(receiver)
					|| StringUtil.isEmpty(phone) || StringUtil.isEmpty(zipCode)
					|| StringUtil.isEmpty(address)) {
				throw new Exception("必填参数为空，请检查cusId/receiver/phone/zipCode/address");
			}
			TblCusAddress addr = new TblCusAddress(Long.valueOf(cusId),
					receiver, phone, zipCode, address, defaultFlag);
			addr.setRemark(remark);
			tblCusAddressService.insert(addr);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "新增成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "新增失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	@RequestMapping("deleteAddress.do")
	public void deleteAddress(HttpServletResponse response, String addressId) {
		logger.info("RelativesHandler.deleteAddress()删除收货地址...");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(addressId)) {
				throw new Exception("addressId不能为空");
			}
			tblCusAddressService.delete(Long.valueOf(addressId));
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, true);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseMap.put(CommonEnums.ReturnKey.SUCCESS.id, false);
			responseMap.put(CommonEnums.ReturnKey.MSG.id, "删除失败！" + e.getMessage());
		}
		writeMsg(response, responseMap);
	}
	
	public static void main(String[] args) {
		TblProduct product = new TblProduct();
		TblBrand brand = new TblBrand();
		product.setTblBrand(brand);
		product.setProductId(3l);
		product.setProductNo("dfa");
		try {
//			BeanUtils.copyProperties(map, product);
//			BeanUtils.populate(product, map);
			Map map = BeanUtils.describe(product);
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
