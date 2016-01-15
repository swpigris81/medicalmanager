/**
 * 
 */
package com.medical.manager.systemset.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.systemset.web.model.TblSysInfo;
import com.medical.manager.systemset.web.query.TblSysInfoParam;

/**
 * @author jason
 *
 * 2015-12-4 上午11:14:54
 * 
 * @version 1.0.0
 */
public interface ITblSysInfoService {

    /**
     * 获取系统设置信息
     * (这里描述这个方法适用条件 – 可选)
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblSysInfo> getSysInfo();

    /**
     * 保存系统设置信息
     * (这里描述这个方法适用条件 – 可选)
     * @param dataMap
     * @throws Exception 
     * @exception
     * @since 1.0.0
     */
    void saveOrUpdateSysInfo(TblSysInfoParam sysInfoParam) throws Exception;
    
    /**
     * 通过参数类型获取系统设置信息
     * (这里描述这个方法适用条件 – 可选)
     * @param type
     * @return
     * @exception
     * @since 1.0.0
     */
    TblSysInfo querySysInfoByType(String type);

}
