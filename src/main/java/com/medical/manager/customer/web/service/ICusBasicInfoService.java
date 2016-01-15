
/**
 * 系统项目名称
 * com.medical.manager.customer.web.service
 * ITblCusBasicInfoService.java
 * 
 * 2015年12月3日-下午8:26:05
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.customer.web.dto.CusBasicInfoDto;
import com.medical.manager.customer.web.model.CusBasicInfo;
import com.medical.manager.stat.web.dto.CusStatDto;
import com.medical.manager.stat.web.model.CusStatAreaAlarm;

/**
 * @description 客户基本信息管理接口
 * 
 * @time 2015年12月3日 下午8:26:05
 * @author Jason
 * @version 1.0.0
 * 
 */

public interface ICusBasicInfoService {
	
	/**
	 * 删除账户 这里是方法描述
	 *
	 * @author Json
	 * @date 2015年12月17日 下午2:50:03
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * @description 查询客户关联基本信息：设备状态、120中心连接状态、信用 积分等
	 * @param cusId
	 * @return CusBasicInfo
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	Map queryRelativesIndex(Long cusId);
	
	/**
	 * @description 查询某市级120中心和隶属该市的区县级120中心集合
	 * @return List<CusBasicInfo>
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List<TblCusBasicExpand> queryCity120(String cityId);
	
	/**
	 * @description 查询某省120中心和隶属该省的市区县级120中心集合
	 * @return List<CusBasicInfo>
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List<TblCusBasicExpand> queryProvince120(String provinceId);
	
	/**
	 * @description 根据id查询客户基本信息
	 * @param id
	 * @return TblCusBasicInfo
	 * @author Jason
	 * @since  1.0.0
	 */
	CusBasicInfo queryById(String id);
	/**
	 * queryByMobileNo 手机查询
	 * (这里描述这个方法适用条件 – 可选)
	 * @param mobileNo
	 * @return 
	 * CusBasicInfo
	 * @exception 
	 * @since  1.0.0
	 */
	CusBasicInfo queryByMobileNo(String mobileNo);
	
	/**
	 * @description 更新客户基本信息
	 * @param basicInfo
	 * @return 
	 * @author Jason
	 * @since  1.0.0
	 */
	void update(CusBasicInfo basicInfo);
	
	/**
	 * @description 批量更新客户基本信息
	 * @param basicInfos
	 * @return 
	 * @author Jason
	 * @since  1.0.0
	 */
	void updateBatch(List<CusBasicInfo> basicInfos);
	
	/**
	 * @description 批量扣减信用积分
	 * @param basicInfos
	 * @return 
	 * @author Jason
	 * @since  1.0.0
	 */
	void updateBatchSubScore(List<CusBasicInfo> basicInfos);
	
	/**
	 * @description 批量扣减信用积分
	 * @param basicInfos
	 * @return 
	 * @author Jason
	 * @since  1.0.0
	 */
	void updateBatchAddScore(List<CusBasicInfo> basicInfos);
	
	/**
	 * @description 新增客户基本信息
	 * @param basicInfo
	 * @return 
	 * @author Jason
	 * @since  1.0.0
	 */
	void insert(CusBasicInfo basicInfo);
	
	/**
	 * @description 分页查询，查询条件保存在info中
	 * @param info 查询条件
	 * @param page 当前页码
	 * @param rows 每页显示行数
	 * @return List<CusBasicInfo>
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List<CusBasicInfo> queryBasicInfoList(CusBasicInfo info, Integer page, Integer rows);
	
	/**
	 * @description 分页查询，查询条件保存在info中
	 * @param paramMap 查询条件
	 * @param page 当前页码
	 * @param rows 每页显示行数
	 * @return List<CusBasicInfo>
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List<CusBasicInfo> queryBasicInfoList4Export(CusBasicInfoDto info, Integer page, Integer rows);
	
	/**
	 * @description 分页查询，查询条件保存在info中
	 * @param info 查询条件
	 * @param page 当前页码
	 * @param rows 每页显示行数
	 * @return List<CusBasicInfo>
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List<CusBasicInfo> queryBasicInfoList(CusBasicInfoDto info, Integer page, Integer rows);

	/**
	 * 根据类型查询待审核认证信息
	 * (这里描述这个方法适用条件 – 可选)
	 * @param id
	 * @return
	 * @exception
	 * @since 1.0.0
	 */
    Integer queryAmbulanceAuthentication(String id);

    /**
     * 查询急救车关联120调度中心
     * (这里描述这个方法的适用条件 - 可选)
     * @param cusId
     * @return
     *@exception
     *@since 1.0.0
     */
    List<CusBasicInfo> query120DispatchCenter(Long cusId);

//	/**
//	 * 这里是方法描述：查询用户隶属市级报警中心数据
//	 *
//	 * @date 2015年12月23日 下午4:14:05
//	 * @param statDto
//	 * @param page
//	 * @param rows
//	 * @return List<CusStatAreaAlarm>
//	 */
//	List<CusStatAreaAlarm> queryStatAreaAlarmListCity(CusStatDto statDto,
//			Integer page, Integer rows);
//
//	/**
//	 * 这里是方法描述：查询用户隶属省级报警中心数据
//	 *
//	 * @date 2015年12月23日 下午4:14:11
//	 * @param statDto
//	 * @param page
//	 * @param rows
//	 * @return List<CusStatAreaAlarm>
//	 */
//	List<CusStatAreaAlarm> queryStatAreaAlarmListProvince(CusStatDto statDto,
//			Integer page, Integer rows);
}
