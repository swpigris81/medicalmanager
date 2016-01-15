/** 
 * <p>Description: []</p>
 * @author  <a href="mailto: swpigris81@126.com">Jason</a>
 * @createDate 2015年12月14日
 */
package com.medical.manager.customer.web.dao;

import org.springframework.stereotype.Repository;

import com.medical.manager.customer.web.model.TblCusLoginSeq;

/**
 * <p>Discription:[用户登录历史流水]</p>
 * @author: Jason
 * @update: 2015年12月14日 Jason[变更描述]
 */
@Repository("cusLoginSeqDao")
public interface ITblCusLoginSeqDao {
    /**
     * <p>Discription:[插入登录流水]</p>
     * @param loginSeq
     * @author: Jason
     * @update: 2015年12月14日 Jason[变更描述]
     */
    public void addNewCusLoginSeq(TblCusLoginSeq loginSeq);
}
