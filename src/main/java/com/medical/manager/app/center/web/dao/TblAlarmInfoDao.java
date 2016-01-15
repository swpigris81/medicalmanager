
/**
 * 系统项目名称
 * com.medical.manager.app.center.web.dao
 * TblAlarmInfoDao.java
 * 
 * 2015年12月15日-上午11:26:13
 * 2015
 * 
 */
 
package com.medical.manager.app.center.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.center.web.model.TblAlarmInfo;


/**
 * 
 * TblAlarmInfoDao 报警信息
 * 
 * 2015年12月15日 上午11:26:13
 * 
 * @version 1.0.0
 * 
 */
@Repository("alarmInfoDao")
public interface TblAlarmInfoDao {
    /**
     * addNewAlarmInfo 新增报警
     * (这里描述这个方法适用条件 – 可选)
     * @param alarmInfo 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void addNewAlarmInfo(TblAlarmInfo alarmInfo);
    /**
     * updateAlarmInfo 修改报警
     * (这里描述这个方法适用条件 – 可选)
     * @param alarmInfo 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void updateAlarmInfo(TblAlarmInfo alarmInfo);
    /**
     * 查询报警详情
     * (这里描述这个方法适用条件 – 可选)
     * @param alarmId
     * @return
     * @exception
     * @since 1.0.0
     */
    public TblAlarmInfo findById(Long alarmId);
    /**
     * <p>Discription:[查询警情信息]</p>
     * @param alarmInfo
     * @return
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    public List<TblAlarmInfo> findAlarmInfo(Map<String, String> paramMap);
    
    /**
     * 查询任务记录
     * (这里描述这个方法适用条件 – 可选)
     * @param paramMap
     * @return
     * @exception
     * @since 1.0.0
     */
    public List<TblAlarmInfo> queryCompleteAlarmTaskList(Map<String, Object> paramMap);
}
