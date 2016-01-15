package com.medical.manager.systemset.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.medical.manager.systemset.web.model.TblSysReceivables;
import com.medical.manager.systemset.web.query.TblSysReceivablesExample;

@Repository("tblSysReceivablesDao")
public interface ITblSysReceivablesDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    int countByExample(TblSysReceivablesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    int deleteByExample(TblSysReceivablesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    int insert(TblSysReceivables record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    int insertSelective(TblSysReceivables record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    List<TblSysReceivables> selectByExample(TblSysReceivablesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    TblSysReceivables selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblSysReceivables record, @Param("example") TblSysReceivablesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblSysReceivables record, @Param("example") TblSysReceivablesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblSysReceivables record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_sys_receivables
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblSysReceivables record);
}