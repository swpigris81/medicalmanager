
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.dao
 * IPropaSiteMessageDao.java
 * 
 * 2015年12月4日-下午1:47:46
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.propaganda.web.model.TblPropaSiteMessage;


/**
 * 
 * IPropaSiteMessageDao
 * 
 * 2015年12月4日 下午1:47:46
 * 
 * @version 1.0.0
 * 
 */
@Repository("siteMessageDao")
public interface IPropaSiteMessageDao {
    /**
     * <p>Discription:[主键查询]</p>
     * @param id
     * @return
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    public TblPropaSiteMessage findById(Long id);

    /**
     * addNewSiteMessage 新增
     * (这里描述这个方法适用条件 – 可选)
     * @param message 
     * @exception 
     * @since  1.0.0
     */
    public void addNewSiteMessage(TblPropaSiteMessage message);
    /**
     * updateSiteMessage 修改
     * (这里描述这个方法适用条件 – 可选)
     * @param message 
     * @exception 
     * @since  1.0.0
     */
    public void updateSiteMessage(TblPropaSiteMessage message);
    /**
     * deleteSiteMessage 删除
     * (这里描述这个方法适用条件 – 可选)
     * @param idList 
     * @exception 
     * @since  1.0.0
     */
    public void deleteSiteMessage(List idList);
}
