
/**
 * 系统项目名称
 * com.supermarket.common.dao
 * IBaseDao.java
 * 
 * 2015年7月7日-下午2:00:04
 * 2015版权所有
 * 
 */
 
package com.medical.manager.common.dao;

import java.util.List;
import java.util.Map;


/**
 * @description 
 * 
 * @time 2015年7月7日 下午2:00:04
 * @author Jason
 * @version 1.0.0
 * 
 */

public interface IBaseDao<T> {
    /**
     * @description queryWithPage 分页查询公共接口
     * @param paramMap 查询参数
     * @param pageNum 当前第几页
     * @param pageSize 每页条数
     * @param namespace 查询SQL_ID
     * @return List<T>
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    public List<T> queryWithPage(Map paramMap, Integer pageNum, Integer pageSize, String namespace);
}
