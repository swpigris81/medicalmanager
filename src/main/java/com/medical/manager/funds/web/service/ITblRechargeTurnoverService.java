
/**
 * 系统项目名称
 * com.medical.manager.funds.web.service
 * ITblRechargeTurnoverService.java
 * 
 * 2015年12月7日-上午11:38:42
 * 2015
 * 
 */
 
package com.medical.manager.funds.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.funds.web.model.TblRechargeTurnover;


/**
 * 
 * ITblRechargeTurnoverService
 * 
 * 2015年12月7日 上午11:38:42
 * 
 * @version 1.0.0
 * 
 */

public interface ITblRechargeTurnoverService {
    /**
     * queryWithPaging 分页查询
     * (这里描述这个方法适用条件 – 可选)
     * @param page
     * @param rows
     * @param paramMap
     * @return List<TblRechargeTurnover>
     * @exception 
     * @since  1.0.0
     */
    public List<TblRechargeTurnover> queryWithPaging(Integer page, Integer rows, Map paramMap);
    /**
     * queryWithPagingExport 导出
     * (这里描述这个方法适用条件 – 可选)
     * @param i
     * @param j
     * @param paramMap
     * @return 
     * List<TblRechargeTurnover>
     * @exception 
     * @since  1.0.0
     */
    public List<TblRechargeTurnover> queryWithPagingExport(Integer page, Integer rows, Map paramMap);
    /**
     * 根据日期查询到期费用
     * (这里描述这个方法适用条件 – 可选)
     * @param nowDate
     * @return
     * @exception
     * @since 1.0.0
     */
    public Integer queryCostExpireNotice(String nowDate,String preDate);

}
