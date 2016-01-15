
/**
 * 系统项目名称
 * com.medical.manager.funds.web.service
 * ITblWithdrawTurnoverService.java
 * 
 * 2015年12月7日-下午12:54:44
 * 2015
 * 
 */
 
package com.medical.manager.funds.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.funds.web.model.TblWithdrawTurnover;


/**
 * 
 * ITblWithdrawTurnoverService
 * 
 * 2015年12月7日 下午12:54:44
 * 
 * @version 1.0.0
 * 
 */

public interface ITblWithdrawTurnoverService {
	
	/**
	 * insert 新增
	 * (这里描述这个方法适用条件 – 可选)
	 * @param turnover
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	void insert(TblWithdrawTurnover turnover) throws Exception;
	
    /**
     * queryWithPaging 分页查询
     * (这里描述这个方法适用条件 – 可选)
     * @param page
     * @param rows
     * @param paramMap
     * @return List<TblWithdrawTurnover> 提现信息
     * @exception 
     * @since  1.0.0
     */
    public List<TblWithdrawTurnover> queryWithPaging(Integer page, Integer rows, Map paramMap);
    /**
     * doUpdateWithdraw 保存提现信息
     * (这里描述这个方法适用条件 – 可选)
     * @param withdrawTurnover 
     * @exception 
     * @since  1.0.0
     */
    public void doUpdateWithdraw(TblWithdrawTurnover withdrawTurnover);
    /**
     * queryWithPagingExport 导出
     * (这里描述这个方法适用条件 – 可选)
     * @param page
     * @param rows
     * @param paramMap
     * @return List<TblWithdrawTurnover>
     * @exception 
     * @since  1.0.0
     */
    public List<TblWithdrawTurnover> queryWithPagingExport(Integer page, Integer rows, Map paramMap);
    
    /**
     * 查询待提现记录
     * (这里描述这个方法的适用条件 - 可选)
     * @return
     *@exception
     *@since 1.0.0
     */
    public Integer queryWaitWithdrawals();

}
