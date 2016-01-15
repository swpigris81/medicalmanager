package com.medical.manager.app.ambulance.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.ambulance.web.model.TblReservePlan;
import com.medical.manager.app.ambulance.web.query.TblReservePlanExample;

@Repository("tblReservePlanDao")
public interface ITblReservePlanDao {
    int countByExample(TblReservePlanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblReservePlan record);

    int insertSelective(TblReservePlan record);

    List<TblReservePlan> selectByExample(TblReservePlanExample example);

    TblReservePlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblReservePlan record);

    int updateByPrimaryKey(TblReservePlan record);
}