
/**
 * 系统项目名称
 * com.medical.manager.funds.web.dao
 * ITblWithdrawTurnoverDao.java
 * 
 * 2015年12月7日-下午12:02:42
 * 2015
 * 
 */
 
package com.medical.manager.funds.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.funds.web.model.TblWithdrawTurnover;


/**
 * 
 * ITblWithdrawTurnoverDao 提现
 * 
 * 2015年12月7日 下午12:02:42
 * 
 * @version 1.0.0
 * 
 */
@Repository("withdrawTurnoverDao")
public interface ITblWithdrawTurnoverDao {
	
	/**
	 * insert 新增
	 * (这里描述这个方法适用条件 – 可选)
	 * @param turnover
	 * @exception 
	 * @since  1.0.0
	 */
	void insert(TblWithdrawTurnover turnover);
	
    /**
     * findById 主键查询
     * (这里描述这个方法适用条件 – 可选)
     * @param id
     * @return TblWithdrawTurnover 提现
     * @exception 
     * @since  1.0.0
     */
    public TblWithdrawTurnover findById(Long id);
    /**
     * findWithdrawTurnoverByIds 批量查询
     * (这里描述这个方法适用条件 – 可选)
     * @param idsList
     * @return List<TblWithdrawTurnover>
     * @exception 
     * @since  1.0.0
     */
    public List<TblWithdrawTurnover> findWithdrawTurnoverByIds(Long idsList);
    /**
     * updateWithdrawTurnover 更新
     * (这里描述这个方法适用条件 – 可选)
     * @param withdrawTurnover 
     * @exception 
     * @since  1.0.0
     */
    public void updateWithdrawTurnover(TblWithdrawTurnover withdrawTurnover);
    /**
     * 查询待提现流水
     * (这里描述这个方法的适用条件 - 可选)
     * @return
     *@exception
     *@since 1.0.0
     */
    public Integer queryWaitWithdrawals();
}
