
/**
 * 系统项目名称
 * com.supermarket.system.web.model
 * TblMenu.java
 * 
 * 2015年6月26日-下午2:05:52
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;


/**
 * @description 菜单信息
 * 
 * @time 2015年6月26日 下午2:05:52
 * @author Jason
 * @version 1.0.0
 * 
 */

public class TblMenu implements Serializable {

    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    
    private Long menuId;
    private String menuName;
    private String menuUrl;
    private Long parentId;
    private String isShow;
    private String menuLevel;
    private String createOper;
    private String createDate;
    private String createTime;
    private String modifyOper;
    private String modifyDate;
    private String modifyTime;
    private String remark;
    
    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuUrl() {
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getCreateOper() {
        return createOper;
    }
    public void setCreateOper(String createOper) {
        this.createOper = createOper;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getModifyOper() {
        return modifyOper;
    }
    public void setModifyOper(String modifyOper) {
        this.modifyOper = modifyOper;
    }
    public String getModifyDate() {
        return modifyDate;
    }
    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
    public String getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getIsShow() {
        return isShow;
    }
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
    public String getMenuLevel() {
        return menuLevel;
    }
    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
