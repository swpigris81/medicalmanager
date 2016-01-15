
/**
 * 系统项目名称
 * com.supermarket.system.web.service.impl
 * MenuServiceImpl.java
 * 
 * 2015年7月2日-上午11:30:42
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.system.web.dao.IMenuDao;
import com.medical.manager.system.web.dao.IRoleDao;
import com.medical.manager.system.web.dto.MenuDto;
import com.medical.manager.system.web.model.TblMenu;
import com.medical.manager.system.web.service.IMenuService;


/**
 * @description 
 * 
 * @time 2015年7月2日 上午11:30:42
 * @author Jason
 * @version 1.0.0
 * 
 */
@Transactional
@Service("menuService")
public class MenuServiceImpl implements IMenuService {
    private Logger logger = Logger.getLogger(MenuServiceImpl.class);
    @Resource
    private IMenuDao menuDao;
    @Resource
    private IRoleDao roleDao;

    @Override
    public List findByUserCode(String userCode) {
        return menuDao.findByUserCode(userCode);
    }

    @Override
    public List findFirstLevelMenuByUserCode(String userCode) {
        return menuDao.findFirstLevelMenuByUserCode(userCode);
    }

    @Override
    public List findChildMenuByMenuId(Long menuId, String userCode) {
        List<MenuDto> list = new ArrayList<MenuDto>();
        return queryMenuByMenuId(menuId, userCode);
    }
    
    private List<MenuDto> queryMenuByMenuId(Long menuId, String userCode){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String menuLevel = "0";
        paramMap.put("menuId", menuId);
        paramMap.put("menuLevel", menuLevel);
        paramMap.put("userCode", userCode);
        List<TblMenu> menuList = menuDao.findChildMenuByMenuId(paramMap);
        List<MenuDto> dtoList = new ArrayList<MenuDto>();
        if(menuList != null && !menuList.isEmpty()){
            for(TblMenu menu : menuList){
                MenuDto dto = recursiveTree(menu, menuLevel, userCode);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
    
    
    private MenuDto recursiveTree(TblMenu menu, String menuLevel, String userCode){
        MenuDto dto = new MenuDto();
        dto.setId(menu.getMenuId() + "");
        dto.setText(menu.getMenuName());
        Map attribute = new HashMap();
        
        try{
            attribute.putAll(PropertyUtils.describe(menu));
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }
        
        dto.setAttributes(attribute);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("menuId", menu.getMenuId());
        paramMap.put("menuLevel", menuLevel);
        paramMap.put("userCode", userCode);
        List<TblMenu> childList = menuDao.findChildMenuByMenuId(paramMap);
        for(TblMenu m : childList){
            MenuDto childDto = recursiveTree(m, menuLevel, userCode);
            dto.getChildren().add(childDto);
        }
        return dto;
    }
    
    
    private MenuDto recursiveTreeForGrid(TblMenu menu, String menuLevel, String userCode){
        MenuDto dto = new MenuDto();
        dto.setId(menu.getMenuId() + "");
        dto.setText(menu.getMenuName());
        Map attribute = new HashMap();
        
        try{
            attribute.putAll(PropertyUtils.describe(menu));
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }
        
        dto.setAttributes(attribute);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("menuId", menu.getMenuId());
        paramMap.put("menuLevel", menuLevel);
        paramMap.put("userCode", userCode);
        List<TblMenu> childList = menuDao.findChildMenuByMenuIdForManager(paramMap);
        for(TblMenu m : childList){
            MenuDto childDto = recursiveTreeForGrid(m, menuLevel, userCode);
            dto.getChildren().add(childDto);
        }
        return dto;
    }

    @Override
    public List findAllMenuListGrid() {
        List<TblMenu> parentMenuList = menuDao.findAllParentMenu();
        List<MenuDto> allMenuList = new ArrayList<MenuDto>();
        if(parentMenuList != null && !parentMenuList.isEmpty()){
            for(TblMenu menu : parentMenuList){
                allMenuList.add(recursiveTreeForGrid(menu, null, null));
            }
        }
        return allMenuList;
    }

    @Override
    public void saveNewMenu(TblMenu menu) {
        menuDao.addNewMenu(menu);
    }

    @Override
    public void saveOldMenu(TblMenu menu) {
        menuDao.updateMenu(menu);
    }

    @Override
    public void doDeleteMenu(Long menuId) {
        menuDao.deleteMenuByMenuId(menuId);
        roleDao.deleteAuthorityByMenuId(menuId);
    }
}
