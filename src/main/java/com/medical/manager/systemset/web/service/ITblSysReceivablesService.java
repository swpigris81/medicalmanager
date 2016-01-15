/**
 * 
 */
package com.medical.manager.systemset.web.service;

import com.medical.manager.systemset.web.model.TblSysReceivables;

/**
 * @author jason
 *
 * 2015-12-4 上午11:17:41
 * 
 * @version 1.0.0
 */
public interface ITblSysReceivablesService {

    /**
     * 获取收款账户设置信息
     * (这里描述这个方法适用条件 – 可选)
     * @return
     * @exception
     * @since 1.0.0
     */
    TblSysReceivables getSysReceivables();

    /**
     * 保存收款账户
     * (这里描述这个方法适用条件 – 可选)
     * @param sysReceivables
     * @exception
     * @since 1.0.0
     */
    void saveSysReceivables(TblSysReceivables sysReceivables);

    /**
     * 更新收款账户设置
     * (这里描述这个方法适用条件 – 可选)
     * @param sysReceivables
     * @exception
     * @since 1.0.0
     */
    void updateSysReceivables(TblSysReceivables sysReceivables);

}
