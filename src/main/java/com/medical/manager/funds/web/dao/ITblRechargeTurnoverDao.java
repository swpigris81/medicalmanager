
/**
 * 系统项目名称
 * com.medical.manager.funds.web.dao
 * ITblRechargeTurnoverDao.java
 * 
 * 2015年12月7日-上午11:28:06
 * 2015
 * 
 */
 
package com.medical.manager.funds.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.funds.web.model.TblRechargeTurnover;


/**
 * 
 * ITblRechargeTurnoverDao 充值流水
 * 
 * 2015年12月7日 上午11:28:06
 * 
 * @version 1.0.0
 * 
 */
@Repository("rechargeTurnoverDao")
public interface ITblRechargeTurnoverDao {
    /**
     * findById 主键查询
     * (这里描述这个方法适用条件 – 可选)
     * @param id
     * @return 
     * TblRechargeTurnover
     * @exception 
     * @since  1.0.0
     */
    public TblRechargeTurnover findById(Long id);
    /**
     * findRechargeTurnover 条件查询
     * (这里描述这个方法适用条件 – 可选)
     * @param paramMap
     * @return List<TblRechargeTurnover>
     * @exception 
     * @since  1.0.0
     */
    public List<TblRechargeTurnover> findRechargeTurnover(Map paramMap);
    /**
     * findRechargeTurnoverByIds 批量查询
     * (这里描述这个方法适用条件 – 可选)
     * @param idsList
     * @return List
     * @exception 
     * @since  1.0.0
     */
    public List<TblRechargeTurnover> findRechargeTurnoverByIds(List idsList);
    /**
     * 根据日期查询到期费用
     * (这里描述这个方法适用条件 – 可选)
     * @param nowDate
     * @return
     * @exception
     * @since 1.0.0
     */
    public Integer queryCostExpireNotice(Map<String, String> paramMap);
    
    /**
     * 保存充值记录
     * (这里描述这个方法适用条件 – 可选)
     * @param rechargeTurnover
     * @exception
     * @since 1.0.0
     */
    void insert(TblRechargeTurnover rechargeTurnover);
    
    /**
     * 查询客户累计成功充值金额
     *
     * @date 2015年12月23日 上午11:40:11
     * @param cusId
     * @return Long
     */
    Long querySumRecharge(Long cusId);
}
