
/**
 * 系统项目名称
 * com.medical.manager.system.web.service
 * ISysAdministrativeService.java
 * 
 * 2015年12月3日-下午3:04:09
 * 2015
 * 
 */
 
package com.medical.manager.system.web.service;

import java.util.List;

import com.medical.manager.system.web.model.TblSysArea;
import com.medical.manager.system.web.model.TblSysCity;
import com.medical.manager.system.web.model.TblSysProvince;


/**
 * 
 * ISysAdministrativeService
 * 
 * 2015年12月3日 下午3:04:09
 * 
 * @version 1.0.0
 * 
 */

public interface ISysAdministrativeService {
    /**
     * findAllProvince 查询所有省份
     * (这里描述这个方法适用条件 – 可选)
     * @return List<TblSysProvince> 省份信息
     * @exception 
     * @since  1.0.0
     */
    public List<TblSysProvince> findAllProvince();
    /**
     * findCityByProvinceid 省份查询对应的市
     * (这里描述这个方法适用条件 – 可选)
     * @param provinceid 省份
     * @return List<TblSysCity> 市
     * @exception 
     * @since  1.0.0
     */
    public List<TblSysCity> findCityByProvinceid(Integer provinceid);
    
    /**
     * searchCountyByCity 通过市查询区
     * (这里描述这个方法适用条件 – 可选)
     * @param cityid 市ID
     * @exception 
     * @since  1.0.0
     */
    public List<TblSysArea> findCountyByCityid(Integer cityid);
}
