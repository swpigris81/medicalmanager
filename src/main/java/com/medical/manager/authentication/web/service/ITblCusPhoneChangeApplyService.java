/**
 * 
 */
package com.medical.manager.authentication.web.service;

import java.util.List;

import com.medical.manager.authentication.web.model.TblCusPhoneChangeApply;

/**
 * @author jason
 * 
 *         下午3:37:04
 * @version 1.0.0
 */
public interface ITblCusPhoneChangeApplyService {

    /**
     * 查询手机变更申请
     * 
     * @param page
     * @param rows
     * @param beginDate
     * @param endDate
     * @param phoneNumber
     * @param checkStatus
     * @return
     */
    List<TblCusPhoneChangeApply> queryPhoneChangeApplyList(Integer page, Integer rows, String beginDate,
            String endDate, String phoneNumber, String checkStatus);

    /**
     * 审核手机变更申请
     * @param phoneChangeApply
     */
    void checkPhoneChangeApply(TblCusPhoneChangeApply phoneChangeApply);

    /**
     * 查询手机号变更待审核数
     * (这里描述这个方法的适用条件 - 可选)
     * @return
     *@exception
     *@since 1.0.0
     */
    Integer queryphoneChangeCheck();

}
