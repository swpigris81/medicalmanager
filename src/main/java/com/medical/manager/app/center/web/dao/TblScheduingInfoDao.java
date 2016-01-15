/** 
 * <p>Description: []</p>
 * @author  <a href="mailto: swpigris81@126.com">Jason</a>
 * @createDate 2015年12月17日
 */
package com.medical.manager.app.center.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.center.web.model.TblScheduingInfo;

/**
 * <p>Discription:[]</p>
 * @author: Jason
 * @update: 2015年12月17日 Jason[变更描述]
 */
@Repository("scheduingInfoDao")
public interface TblScheduingInfoDao {
    /**
     * <p>Discription:[查询调度信息]</p>
     * @param paramMap
     * @return
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    public List<TblScheduingInfo> findScheduingInfo(Map paramMap);
}
