
/**
 * 系统项目名称
 * com.medical.manager.system.web.dao
 * ISysAdministrativeDao.java
 * 
 * 2015年12月3日-下午2:56:46
 * 2015
 * 
 */
 
package com.medical.manager.system.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.system.web.model.TblSysArea;
import com.medical.manager.system.web.model.TblSysCity;
import com.medical.manager.system.web.model.TblSysProvince;


/**
 * 
 * ISysAdministrativeDao 行政区划数据库
 * 
 * 2015年12月3日 下午2:56:46
 * 
 * @version 1.0.0
 * 
 */
@Repository("sysAdministrativeDao")
public interface ISysAdministrativeDao {
    /**
     * findProvinceById 主键查询省份信息
     * (这里描述这个方法适用条件 – 可选)
     * @param provinceid 主键
     * @return TblSysProvince 省份信息
     * @exception 
     * @since  1.0.0
     */
    public TblSysProvince findProvinceById(Integer provinceid);
    /**
     * findProvinceAndCity 关联查询省份、市、区县
     * (这里描述这个方法适用条件 – 可选)
     * @param paramMap 查询参数
     * @return List<TblSysProvince> 省份、市、区县信息
     * @exception 
     * @since  1.0.0
     */
    public List<TblSysProvince> findProvinceAndCity(Map paramMap);
    /**
     * findAllProvince 查询所有省份
     * (这里描述这个方法适用条件 – 可选)
     * @return List<TblSysProvince> 省份信息
     * @exception 
     * @since  1.0.0
     */
    public List<TblSysProvince> findAllProvince();
    /**
     * findCityById 主键查询市
     * (这里描述这个方法适用条件 – 可选)
     * @param cityid 主键
     * @return TblSysCity 市
     * @exception 
     * @since  1.0.0
     */
    public TblSysCity findCityById(Integer cityid);
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
     * findCityAndArea 关联查询市、区县
     * (这里描述这个方法适用条件 – 可选)
     * @param paramMap 查询参数
     * @return List<TblSysCity> 市、区县
     * @exception 
     * @since  1.0.0
     */
    public List<TblSysCity> findCityAndArea(Map paramMap);
    /**
     * findAreaById 主键查询区县
     * (这里描述这个方法适用条件 – 可选)
     * @param areaid 主键
     * @return TblSysArea 区县
     * @exception 
     * @since  1.0.0
     */
    public TblSysArea findAreaById(Integer areaid);
    /**
     * 
     * findAreaByCityId 城市主键查询关联区县
     * (这里描述这个方法适用条件 – 可选)
     * @param cityid 城市主键
     * @return List<TblSysArea> 区县
     * @exception 
     * @since  1.0.0
     */
    public List<TblSysArea> findAreaByCityId(Integer cityid);
    
}
