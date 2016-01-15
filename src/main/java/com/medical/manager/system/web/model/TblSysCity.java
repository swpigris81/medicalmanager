
/**
 * 系统项目名称
 * com.medical.manager.system.web.model
 * TblSysCity.java
 * 
 * 2015年12月3日-下午2:38:29
 * 2015
 * 
 */
 
package com.medical.manager.system.web.model;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * TblSysCity 行政区域地州市信息表
 * 
 * 2015年12月3日 下午2:38:29
 * 
 * @version 1.0.0
 * 
 */
public class TblSysCity implements Serializable {

    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    /**
     * 城市ID
     */
    private Integer cityid;
    /**
     * 省行政区划ID
     */
    private Integer provinceid;
    /**
     * 城市名称
     */
    private String city;
    /**
     * 区域列表
     */
    private List<TblSysArea> areaList;
    
    public Integer getCityid() {
        return cityid;
    }
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }
    public Integer getProvinceid() {
        return provinceid;
    }
    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public List<TblSysArea> getAreaList() {
        return areaList;
    }
    public void setAreaList(List<TblSysArea> areaList) {
        this.areaList = areaList;
    }
    

}
