
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.dao
 * ITblCusDeviceDao.java
 * 
 * 2015年12月15日-下午1:48:28
 * 2015
 * 
 */
 
package com.medical.manager.app.relatives.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.relatives.web.model.TblCusDevice;


/**
 * 
 * ITblCusDeviceDao
 * 
 * 2015年12月15日 下午1:48:28
 * 
 * @version 1.0.0
 * 
 */
@Repository("tblCusDeviceDao")
public interface ITblCusDeviceDao {
    /**
     * findById 主键查询
     * (这里描述这个方法适用条件 – 可选)
     * @param id
     * @return TblCusDevice
     * @exception 
     * @since  1.0.0
     */
    public TblCusDevice findById(Long id);
    
    /**
     * 新增
     * (这里描述这个方法适用条件 – 可选)
     * @param device
     * @return
     * @exception 
     * @since  1.0.0
     */
    void insert(TblCusDevice device);
    
    /**
     * 修改
     * (这里描述这个方法适用条件 – 可选)
     * @param device
     * @return
     * @exception 
     * @since  1.0.0
     */
    void update(TblCusDevice device);
    
    /**
     * 查询已开通设备列表
     * (这里描述这个方法适用条件 – 可选)
     * @param device
     * @return
     * @exception 
     * @since  1.0.0
     */
    List<TblCusDevice> queryOpenDeviceList(Long use_id);
    
    /**
     * 根据电话查询设备
     * @description 
     * @param phone
     * @return TblCusDevice
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    List<TblCusDevice> queryDevicesByPhone(String phone);
}
