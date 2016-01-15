
/**
 * 系统项目名称
 * com.supermarket.system.web.service.impl
 * RoleServiceImpl.java
 * 
 * 2015年7月7日-上午11:57:38
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.system.web.dao.IRoleDao;
import com.medical.manager.system.web.dao.IUserDao;
import com.medical.manager.system.web.model.TblRole;
import com.medical.manager.system.web.model.TblRoleAuthority;
import com.medical.manager.system.web.service.IRoleService;


/**
 * @description 
 * 
 * @time 2015年7月7日 上午11:57:38
 * @author Jason
 * @version 1.0.0
 * 
 */
@Transactional
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    private Logger logger = Logger.getLogger(RoleServiceImpl.class);
    @Resource
    private IRoleDao roleDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private IBaseDao<TblRole> baseDao;
    @Override
    public List<TblRole> queryRoleListWithPaging(Integer pageNum, Integer pageSize) {
        logger.info("当前查询第：" + pageNum + " 页，每页：" + pageSize + "：条数据");
        return baseDao.queryWithPage(null, pageNum, pageSize, "findAllRoleWithPaging");
    }

    @Override
    public List<TblRole> queryRoleListWithPaging(Map paramMap, Integer pageNum, Integer pageSize) {
        logger.info("当前查询第：" + pageNum + " 页，每页：" + pageSize + "：条数据");
        return baseDao.queryWithPage(paramMap, pageNum, pageSize, "findAllRoleWithPaging");
    }
    
    /**
     * @description roleAuthorityList 查询角色权限列表
     * @param roleId 角色编号
     * @return List
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List roleAuthorityList(Long roleId){
        return roleDao.findMenuByRoleId(roleId);
    }

    @Override
    public void saveAuthority(Long roleId, String[] menuId) {
        //先删除，再插入
        roleDao.deleteAuthority(roleId);
        //批量插入
        if(menuId != null && menuId.length > 0){
            List list = new ArrayList();
            for(String menu : menuId){
                TblRoleAuthority authority = new TblRoleAuthority();
                authority.setRoleId(roleId);
                authority.setMenuId(NumberUtils.toLong(menu));
                authority.setCreateDate(DateUtil.getNowDate());
                authority.setCreateTime(DateUtil.getNowTime());
                authority.setModifyDate(DateUtil.getNowDate());
                authority.setModifyTime(DateUtil.getNowTime());
                authority.setRemark("角色权限修改");
                list.add(authority);
            }
            roleDao.saveAuthorityBatch(list);
        }
    }

    @Override
    public List queryAllRole() {
        return roleDao.findAllRoleWithoutPaging();
    }

    @Override
    public void saveNewRole(TblRole role) {
        roleDao.addNewRole(role);
    }

    @Override
    public void doUpdateRole(TblRole role) {
        roleDao.updateRole(role);
    }

    @Override
    public void doDeleteRole(Long roleId) {
        roleDao.deleteRoleByRoleId(roleId);
        roleDao.deleteAuthority(roleId);
        userDao.deleteUserRoleByRoleId(roleId);
    }

}
