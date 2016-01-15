
/**
 * 系统项目名称
 * com.supermarket.system.web.dao
 * IUserSecret.java
 * 
 * 2015年6月30日-上午11:29:26
 * 2015版权所有
 * 
 */
 
package com.medical.manager.system.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.system.web.model.TblUserSecret;


/**
 * @description 用户保密信息数据持久化
 * 
 * @time 2015年6月30日 上午11:29:26
 * @author Jason
 * @version 1.0.0
 * 
 */
@Repository("userSecretDao")
public interface IUserSecretDao {
    /**
     * @description findByUserCode 使用用户名查找该用户对应的密码信息
     * @param userCode 用户名
     * @return TblUserSecret
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public TblUserSecret findByUserCode(String userCode);
    
    /**
     * @description updateSecret 修改用户密码
     * @param secret 密码对象
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void updateSecret(TblUserSecret secret);
    /**
     * @description addNewSecret 新增用户密码
     * @param secret 密码
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void addNewSecret(TblUserSecret secret);
    /**
     * @description deleteUserSecretByUserId 删除用户密码
     * @param userId 用户
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void deleteUserSecretByUserId(Long userId);
    
    /**
     * @description deleteUserSecretByUserIds 删除用户密码
     * @param userId 用户
     * @return void
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public void deleteUserSecretByUserIds(List userIdList);
}
