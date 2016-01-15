/** 
 * <p>Description: []</p>
 * @author  <a href="mailto: swpigris81@126.com">Jason</a>
 * @createDate 2015年12月14日
 */
package com.medical.manager.app.center.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.center.web.model.TblCenterPoliceInfo;

/**
 * <p>Discription:[120中心的用户信息]</p>
 * @author: Jason
 * @update: 2015年12月14日 Jason[变更描述]
 */
@Repository("centerPoliceInfoDao")
public interface TblCenterPoliceInfoDao {
    /**
     * <p>Discription:[主键查询120中心用户]</p>
     * @param id
     * @return
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    public TblCenterPoliceInfo findById(Long id);
    /**
     * <p>Discription:[手机号码查询120中心用户]</p>
     * @param mobileNo
     * @return
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    public TblCenterPoliceInfo findByMobileNo(String mobileNo);
    /**
     * <p>Discription:[新增120中心用户]</p>
     * @param policeInfo
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    public void addNewCenterPoliceInfo(TblCenterPoliceInfo policeInfo);
    /**
     * <p>Discription:[用户登录查询]</p>
     * @param paramMap 查询条件
     * @return
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    public List<TblCenterPoliceInfo> findPoliceLogin(Map paramMap);
    /**
     * <p>Discription:[更新用户信息]</p>
     * @param policeInfo
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    public void updatePolice(TblCenterPoliceInfo policeInfo);
}
