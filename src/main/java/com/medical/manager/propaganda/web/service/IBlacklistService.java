
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.service
 * BlacklistService.java
 * 
 * 2015年12月4日-下午2:22:02
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.propaganda.web.model.TblPropaBlacklist;


/**
 * 
 * BlacklistService
 * 
 * 2015年12月4日 下午2:22:02
 * 
 * @version 1.0.0
 * 
 */

public interface IBlacklistService {
    /**
     * queryWithPaging 分页查询黑名单
     * (这里描述这个方法适用条件 – 可选)
     * @param pageNum
     * @param pageSize
     * @param paramMap
     * @return List<TblPropaBlacklist> 黑名单
     * @exception 
     * @since  1.0.0
     */
    public List<TblPropaBlacklist> queryWithPaging(Integer pageNum, Integer pageSize, Map paramMap);
    /**
     * <p>Discription:[保存新增黑名单]</p>
     * @param blacklist
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    public void doSaveNewBlacklist(TblPropaBlacklist blacklist);
    /**
     * <p>Discription:[修改黑名单]</p>
     * @param blacklist
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    public void doUpdateBlacklist(TblPropaBlacklist blacklist);
    /**
     * <p>Discription:[删除黑名单]</p>
     * @param blackIdList
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    public void doDeleteBlacklist(List blackIdList);
}
