
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.dao
 * IPropaBlacklistDao.java
 * 
 * 2015年12月4日-下午3:11:18
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.propaganda.web.model.TblPropaBlacklist;


/**
 * 
 * IPropaBlacklistDao
 * 
 * 2015年12月4日 下午3:11:18
 * 
 * @version 1.0.0
 * 
 */
@Repository("propaBlacklistDao")
public interface IPropaBlacklistDao {
    /**
     * addNewBlacklist 新增
     * (这里描述这个方法适用条件 – 可选)
     * @param blackList 
     * @exception 
     * @since  1.0.0
     */
    public void addNewBlacklist(TblPropaBlacklist blackList);
    /**
     * updateBlacklist 修改
     * (这里描述这个方法适用条件 – 可选)
     * @param blackList 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void updateBlacklist(TblPropaBlacklist blackList);
    /**
     * deleteBlacklist 删除
     * (这里描述这个方法适用条件 – 可选)
     * @param idList 
     * @exception 
     * @since  1.0.0
     */
    public void deleteBlacklist(List idList);
}
