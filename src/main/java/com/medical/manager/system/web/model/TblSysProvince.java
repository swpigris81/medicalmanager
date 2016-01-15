
/**
 * 系统项目名称
 * com.medical.manager.system.web.model
 * TblSysProvince.java
 * 
 * 2015年12月3日-下午2:39:38
 * 2015
 * 
 */
 
package com.medical.manager.system.web.model;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * TblSysProvince 行政区域省份信息表
 * 
 * 2015年12月3日 下午2:39:38
 * 
 * @version 1.0.0
 * 
 */

public class TblSysProvince implements Serializable {
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    /**
     * 省行政区划ID
     */
    private Integer provinceid;
    /**
     * 省名称
     */
    private String province;
    /**
     * 城市列表
     */
    private List<TblSysCity> cityList;
    
    public Integer getProvinceid() {
        return provinceid;
    }
    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public List<TblSysCity> getCityList() {
        return cityList;
    }
    public void setCityList(List<TblSysCity> cityList) {
        this.cityList = cityList;
    }
    
    
}
