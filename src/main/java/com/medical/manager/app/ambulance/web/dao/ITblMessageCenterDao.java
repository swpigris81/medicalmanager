package com.medical.manager.app.ambulance.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.ambulance.web.model.TblMessageCenter;
import com.medical.manager.app.ambulance.web.query.TblMessageCenterExample;

@Repository("tblMessageCenterDao")
public interface ITblMessageCenterDao {
    int countByExample(TblMessageCenterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblMessageCenter record);

    int insertSelective(TblMessageCenter record);

    List<TblMessageCenter> selectByExample(TblMessageCenterExample example);
    TblMessageCenter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblMessageCenter record);

    int updateByPrimaryKey(TblMessageCenter record);
}