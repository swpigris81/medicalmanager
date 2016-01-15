package com.medical.manager.authentication.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.medical.manager.authentication.web.model.TblCusBasicExpand;
import com.medical.manager.authentication.web.query.TblCusBasicExpandExample;

@Repository("cusBasicExpandDao")
public interface ITblCusBasicExpandDao {
	
	void delete(Long cusId);
	
    int countByExample(TblCusBasicExpandExample example);

    int deleteByExample(TblCusBasicExpandExample example);

    int insert(TblCusBasicExpand record);

    int insertSelective(TblCusBasicExpand record);

    List<TblCusBasicExpand> selectByExample(TblCusBasicExpandExample example);
    
    List<TblCusBasicExpand> selectByCustId(Long cusId);
    
    TblCusBasicExpand selectById(String id);

    int updateByExampleSelective(@Param("record") TblCusBasicExpand record, @Param("example") TblCusBasicExpandExample example);

    int updateByExample(@Param("record") TblCusBasicExpand record, @Param("example") TblCusBasicExpandExample example);
    
    void updateByKeySelective(@Param("record")TblCusBasicExpand record);

    void checkCusBasicExpand(TblCusBasicExpand record);
}