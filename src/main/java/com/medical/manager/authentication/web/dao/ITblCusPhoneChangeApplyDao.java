package com.medical.manager.authentication.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.medical.manager.authentication.web.model.TblCusPhoneChangeApply;
import com.medical.manager.authentication.web.query.TblCusPhoneChangeApplyExample;

@Repository("cusPhoneChangeApplyDao")
public interface ITblCusPhoneChangeApplyDao {
    int countByExample(TblCusPhoneChangeApplyExample example);

    int deleteByExample(TblCusPhoneChangeApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblCusPhoneChangeApply record);

    int insertSelective(TblCusPhoneChangeApply record);

    List<TblCusPhoneChangeApply> selectByExample(TblCusPhoneChangeApplyExample example);

    TblCusPhoneChangeApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TblCusPhoneChangeApply record, @Param("example") TblCusPhoneChangeApplyExample example);

    int updateByExample(@Param("record") TblCusPhoneChangeApply record, @Param("example") TblCusPhoneChangeApplyExample example);

    int updateByPrimaryKeySelective(TblCusPhoneChangeApply record);

    int updateByPrimaryKey(TblCusPhoneChangeApply record);

    /**
     * 查询手机号变更待审核数
     * (这里描述这个方法的适用条件 - 可选)
     * @return
     *@exception
     *@since 1.0.0
     */
    Integer queryphoneChangeCheck();

}