/**
 * 
 */
package com.medical.manager.authentication.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.authentication.web.model.TblCusBasicExpand;

/**
 * @author jason
 *
 * 下午3:38:36
 *  @version 1.0.0
 */
public interface ITblCusBasicExpandService {

    /**
     * 查询客户注册信息
     * @param page
     * @param rows
     * @param paramMap
     * @return
     */
    List<TblCusBasicExpand> queryCusBasicExpandList(Integer page, Integer rows, Map<String, String> paramMap);

    /**
     * 审核客户基本资料
     * @param cusBasicExpand
     */
    void checkCusBasicExpand(TblCusBasicExpand cusBasicExpand);

    /**
     * 保存客户基本资料
     * @param response
     * @param cusBasicExpand
     */
    void saveCusBasicExpand(TblCusBasicExpand cusBasicExpand);

    /**
     * 修改客户基本资料
     * @param response
     * @param cusBasicExpand
     */
    void updateCusBasicExpand(TblCusBasicExpand cusBasicExpand);

}
