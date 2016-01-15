
/**
 * 系统项目名称
 * com.medical.manager.app.relatives.web.dao
 * ITblPropaNewsDao.java
 * 
 * 2015年12月19日-下午8:39:47
 * 2015版权所有
 * 
 */

package com.medical.manager.app.relatives.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.relatives.web.model.TblPropaNews;

/**
 * @description
 * 
 * @time 2015年12月19日 下午8:39:47
 * @author Jason
 * @version 1.0.0
 * 
 */

@Repository("tblPropaNewsDao")
public interface ITblPropaNewsDao {
	/**
	 * 根据id查询
	 * 
	 * @description
	 * @param id
	 * @return TblPropaNews
	 * @author Jason
	 * @since 1.0.0
	 */
	TblPropaNews findById(Long id);

	/**
	 * @description
	 * @param news
	 * @author Jason
	 * @since 1.0.0
	 */
	void insert(TblPropaNews news);

	/**
	 * 
	 * @description
	 * @param news
	 * @author Jason
	 * @since 1.0.0
	 */
	void update(TblPropaNews news);

	/**
	 * @description
	 * @param id
	 * @exception @author
	 *                Jason
	 * @since 1.0.0
	 */
	void delete(Long id);

	/**
	 * 查询某个时间之后的新闻
	 * @description 
	 * @param time
	 * @return List<TblPropaNews>
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List<TblPropaNews> queryTimeAfter(String time);
	
	/**
	 * 查询最新num条新闻
	 * @description 
	 * @param number
	 * @return
	 * List<TblPropaNews>
	 * @exception 
	 * @author Jason
	 * @since  1.0.0
	 */
	List<TblPropaNews> queryLatelyNumNews(Integer number);
}
