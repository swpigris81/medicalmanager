package com.medical.manager.systemset.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.medical.manager.systemset.web.model.TblSysInfo;
import com.medical.manager.systemset.web.query.TblSysInfoExample;

@Repository("tblSysInfoDao")
public interface ITblSysInfoDao {
	
	TblSysInfo queryByCusId(Long cusId);
	
	/**
	 * 查询急救车或者120中心充值返现参数设置
	 *
	 * @date 2015年12月23日 下午1:33:48
	 * @param cusId
	 * @return TblSysInfo
	 */
	TblSysInfo queryBack4AmbOr120ByCusId(Long cusId);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    int countByExample(TblSysInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    int deleteByExample(TblSysInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    int insert(TblSysInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    int insertSelective(TblSysInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    List<TblSysInfo> selectByExample(TblSysInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    TblSysInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblSysInfo record, @Param("example") TblSysInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblSysInfo record, @Param("example") TblSysInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblSysInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblSysInfo record);

    /**
     * 批量新增系统设置信息
     * @param sysInfos
     */
	void insertBatch(List<TblSysInfo> sysInfos);
	
	/**
     * 批量更新系统设置信息
     * @param sysInfos
     */
	void updateBatch(List<TblSysInfo> sysInfos);
}