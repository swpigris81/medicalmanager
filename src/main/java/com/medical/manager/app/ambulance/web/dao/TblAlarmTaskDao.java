package com.medical.manager.app.ambulance.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.medical.manager.app.ambulance.web.model.TblAlarmTask;
import com.medical.manager.app.ambulance.web.query.TblAlarmTaskExample;

@Repository("tblAlarmTaskDao")
public interface TblAlarmTaskDao {
    int countByExample(TblAlarmTaskExample example);

    int deleteByExample(TblAlarmTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblAlarmTask record);

    int insertSelective(TblAlarmTask record);

    List<TblAlarmTask> selectByExample(TblAlarmTaskExample example);

    TblAlarmTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblAlarmTask record);

    int updateByPrimaryKey(TblAlarmTask record);

    /**
     * 查询报警任务
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId 
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblAlarmTask> queryAlarmTaskList(Long cusId);
}