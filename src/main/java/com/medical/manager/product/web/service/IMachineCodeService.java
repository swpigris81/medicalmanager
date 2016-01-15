/**
 * 
 */
package com.medical.manager.product.web.service;

import java.util.List;

import com.medical.manager.product.web.model.TblMachineCode;
import com.medical.manager.product.web.model.TblMachineUse;

/**
 * @author Jason
 *
 * 2015-12-2 上午9:36:49
 * 
 * @version 1.0.0
 */
public interface IMachineCodeService {

    /**
     * 查询机器码
     * (这里描述这个方法适用条件 – 可选)
     * @param pageNum
     * @param pageSize
     * @param useStatus
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblMachineCode> queryMachineCodeList(Integer pageNum, Integer pageSize, String useStatus);

    /**
     * 批量生成机器码
     * (这里描述这个方法适用条件 – 可选)
     * @exception
     * @since 1.0.0
     */
    void produceMachineCodes(Integer number);

    /**
     * 查询机器码使用状态
     * (这里描述这个方法适用条件 – 可选)
     * @param pageNum
     * @param pageSize
     * @param phoneNumber
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblMachineUse> queryMachineUseList(Integer pageNum, Integer pageSize, String phoneNumber);

}
