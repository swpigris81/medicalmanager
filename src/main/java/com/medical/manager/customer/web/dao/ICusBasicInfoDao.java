
/**
 * 系统项目名称
 * com.medical.manager.customer.web.dao
 * TblCusBasicInfoDao.java
 * 
 * 2015年12月3日-下午8:14:26
 * 2015版权所有
 * 
 */

package com.medical.manager.customer.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.customer.web.model.CusBasicInfo;

/**
 * @description 客户基本信息管理DAO
 * 
 * @time 2015年12月3日 下午8:14:26
 * @author Jason
 * @version 1.0.0
 * 
 */

@Repository("cusBasicInfoDao")
public interface ICusBasicInfoDao {
	
	/**
	 * 
	 * 这里是方法描述
	 *
	 * @author Json
	 * @date 2015年12月17日 下午2:50:51
	 * @param id
	 */
	public void delete(Long id);
	
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
	List<TblCusBasicExpand> queryCity120(Map<String, String> paramMap);
	
	/**
	 * @description 查询某省120中心和隶属该省的市区县级120中心集合
	 * @return List<CusBasicInfo>
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List<TblCusBasicExpand> queryProvince120(Map<String, String> paramMap);
	/**
	 * <p>Discription:[条件查询全部120中心集合]</p>
	 * @param paramMap 查询条件
	 * @return
	 * @author: Jason
	 * @update: 2015年12月14日 Jason[变更描述]
	 */
	List<TblCusBasicExpand> queryCenterList(Map<String, String> paramMap);
	/**
	 * queryCenterListByParams 参数查询120中心信息
	 * (这里描述这个方法适用条件 – 可选)
	 * @param paramMap
	 * @return List<CusBasicInfo>
	 * @exception 
	 * @since  1.0.0
	 */
	List<CusBasicInfo> queryCenterListByParams(Map<String, String> paramMap);

	/**
	 * @description 根据id查询客户基本信息
	 * @param id
	 * @return TblCusBasicInfo
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	CusBasicInfo queryById(Long id);
	/**
	 * <p>Discription:[查询客户信息]</p>
	 * @param paramMap
	 * @return
	 * @author: Jason
	 * @update: 2015年12月17日 Jason[变更描述]
	 */
	List<CusBasicInfo> queryBasicInfoList(Map paramMap);
	
	/**
     * @description 根据手机号查询客户基本信息
     * @param mobileNo
     * @return TblCusBasicInfo
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    CusBasicInfo queryByMobileNo(String mobileNo);
    
    
	
	/**
	 * @description 更新客户基本信息
	 * @param basicInfo
	 * @return TblCusBasicInfo
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void update(CusBasicInfo basicInfo);
	
	/**
	 * @description 更新信用积分
	 * @param basicInfo
	 * @return TblCusBasicInfo
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void updateCreditScore(CusBasicInfo basicInfo);
	
	/**
	 * @description 增加信用积分
	 * @param basicInfo
	 * @return TblCusBasicInfo
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void updateAddScore(CusBasicInfo basicInfo);
	
	/**
	 * @description 新增户基本信息
	 * @param basicInfo
	 * @return TblCusBasicInfo
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	void insert(CusBasicInfo basicInfo);

	/**
	 * 查询待认证审核
	 * (这里描述这个方法适用条件 – 可选)
	 * @param type
	 * @return
	 * @exception
	 * @since 1.0.0
	 */
    Integer queryAmbulanceAuthentication(String type);

    /**
     * 查询急救车关联120调度中心
     * (这里描述这个方法的适用条件 - 可选)
     * @param cusId
     * @return
     *@exception
     *@since 1.0.0
     */
    List<CusBasicInfo> query120DispatchCenter(Long cusId);
    
    /**
     * 这里是方法描述：查询所有急救车
     *
     * @author Json
     * @time 2016年1月13日 上午10:21:06
     * @return List<CusBasicInfo>
     */
    List<CusBasicInfo> queryAllAmbulance();
}
