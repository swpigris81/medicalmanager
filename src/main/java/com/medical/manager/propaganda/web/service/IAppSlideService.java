
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.service
 * IAppSlideService.java
 * 
 * 2015年12月4日-下午2:20:37
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.propaganda.web.model.TblPropaAppSlide;


/**
 * 
 * IAppSlideService
 * 
 * 2015年12月4日 下午2:20:37
 * 
 * @version 1.0.0
 * 
 */

public interface IAppSlideService {
    /**
     * queryWithPaging 分页查询幻灯片
     * (这里描述这个方法适用条件 – 可选)
     * @param pageNum
     * @param pageSize
     * @param paramMap
     * @return 
     * List<TblPropaAppSlide>
     * @exception 
     * @since  1.0.0
     */
    List<TblPropaAppSlide> queryWithPaging(Integer pageNum, Integer pageSize, Map paramMap);
    /**
     * doSaveNewAppSlide 新增幻灯片
     * (这里描述这个方法适用条件 – 可选)
     * @param appSlide 
     * @exception 
     * @since  1.0.0
     */
    public void doSaveNewAppSlide(TblPropaAppSlide appSlide);
    
    /**
     * doUpdateAppSlide 修改幻灯片
     * (这里描述这个方法适用条件 – 可选)
     * @param appSlide 
     * @exception 
     * @since  1.0.0
     */
    public void doUpdateAppSlide(TblPropaAppSlide appSlide);
    /**
     * <p>Discription:[删除幻灯片]</p>
     * @param slideIdList
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    public void doDeleteAppSlide(List slideIdList);

}
