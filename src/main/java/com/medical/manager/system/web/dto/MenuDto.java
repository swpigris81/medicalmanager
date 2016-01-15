
/**
 * 系统项目名称
 * com.supermarket.system.web.dto
 * MenuDto.java
 * 
 * 2015年7月3日-下午4:48:50
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.medical.manager.system.web.model.TblMenu;


/**
 * @description 
 * 
 * @time 2015年7月3日 下午4:48:50
 * @author Jason
 * @version 1.0.0
 * 
 */

public class MenuDto implements Serializable {
    private String id;
    private String text;
    /**
     * open or closed
     */
    private String state;
    private List children = new ArrayList();
    private boolean checked;
    private String iconCls;
    private Map attributes;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public List getChildren() {
        return children;
    }
    public void setChildren(List children) {
        this.children = children;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public String getIconCls() {
        return iconCls;
    }
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
    public Map getAttributes() {
        return attributes;
    }
    public void setAttributes(Map attributes) {
        this.attributes = attributes;
    }
    
    public static MenuDto copyFromMenu(TblMenu menu){
        MenuDto dto = new MenuDto();
        dto.setId(menu.getMenuId() + "");
        dto.setText(menu.getMenuName());
        Map attribute = new HashMap();
        attribute.put("url", menu.getMenuUrl());
        dto.setAttributes(attribute);
        return dto;
    }
}
