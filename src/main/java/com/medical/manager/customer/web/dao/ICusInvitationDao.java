/** 
 * <p>Description: []</p>
 * @author  <a href="mailto: swpigris81@126.com">Jason</a>
 * @createDate 2015年12月14日
 */
package com.medical.manager.customer.web.dao;

import org.springframework.stereotype.Repository;

import com.medical.manager.customer.web.model.TblCusInvitation;

/**
 * <p>Discription:[邀请码]</p>
 * @author: Jason
 * @update: 2015年12月14日 Jason[变更描述]
 */
@Repository("invitationDao")
public interface ICusInvitationDao {
    /**
     * <p>Discription:[新增邀请信息]</p>
     * @param invitation
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    public void addNewCusInvitation(TblCusInvitation invitation);
    
    /**
     * 根据客户id更新返现返现标识和返现时间 
     *
     * @date 2015年12月23日 上午11:14:29
     * @param invitation
     * void
     */
    void updateBackCashByCusId(TblCusInvitation invitation);
    
    /**
     * 根据客户id查询是否已返现 
     *
     * @date 2015年12月23日 上午11:15:10
     * @param cusId
     * @return blCusInvitation
     */
    TblCusInvitation queryBackCashByCusId(Long cusId);
}
