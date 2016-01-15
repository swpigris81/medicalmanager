
/**
 * 系统项目名称
 * com.medical.manager.system.web.model
 * TblSysArea.java
 * 
 * 2015年12月3日-下午2:35:41
 * 2015
 * 
 */
 
package com.medical.manager.system.web.model;

import java.io.Serializable;


/**
 * 
 * TblSysArea 行政区域县区信息表
 * 
 * 2015年12月3日 下午2:35:41
 * 
 * @version 1.0.0
 * 
 */

public class TblSysArea implements Serializable {
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    /**
     * 县区ID
     */
    private Integer areaid;
    /**
     * 县区名称
     */
    private String area;
    /**
     * 市ID
     */
    private Integer cityid;
    public Integer getAreaid() {
        return areaid;
    }
    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public Integer getCityid() {
        return cityid;
    }
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }
    
}
