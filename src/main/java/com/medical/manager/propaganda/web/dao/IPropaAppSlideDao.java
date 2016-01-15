
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.dao
 * IPropaAppSlideDao.java
 * 
 * 2015年12月4日-下午3:10:39
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.propaganda.web.model.TblPropaAppSlide;


/**
 * 
 * IPropaAppSlideDao
 * 
 * 2015年12月4日 下午3:10:39
 * 
 * @version 1.0.0
 * 
 */
@Repository("propaAppSlideDao")
public interface IPropaAppSlideDao {
    /**
     * addNewAppSlide 新增
     * (这里描述这个方法适用条件 – 可选)
     * @param appSlide 
     * @exception 
     * @since  1.0.0
     */
    public void addNewAppSlide(TblPropaAppSlide appSlide);
    /**
     * updateAppSlide 修改
     * (这里描述这个方法适用条件 – 可选)
     * @param appSlide 
     * @exception 
     * @since  1.0.0
     */
    public void updateAppSlide(TblPropaAppSlide appSlide);
    /**
     * deleteAppSlide 删除
     * (这里描述这个方法适用条件 – 可选)
     * @param idsList 
     * @exception 
     * @since  1.0.0
     */
    public void deleteAppSlide(List idsList);
    /**
     * <p>Discription:[查询指定幻灯片]</p>
     * @param idsList
     * @return
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    public List<TblPropaAppSlide> findAppSlideByIds(List idsList);
}
