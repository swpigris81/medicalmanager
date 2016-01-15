package com.medical.manager.product.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.medical.manager.product.web.model.TblMachineCode;
import com.medical.manager.product.web.query.TblMachineCodeExample;

@Repository("tblMachineCodeDao")
public interface ITblMachineCodeDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    int countByExample(TblMachineCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    int deleteByExample(TblMachineCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    int insert(TblMachineCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    int insertSelective(TblMachineCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    List<TblMachineCode> selectByExample(TblMachineCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    TblMachineCode selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblMachineCode record, @Param("example") TblMachineCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblMachineCode record, @Param("example") TblMachineCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblMachineCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_machine_code
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblMachineCode record);
    
    /**
     * 根据机器码查询未使用的机器码集合
     * 这里是方法描述
     *
     * @author Json
     * @date 2015年12月16日 下午12:59:24
     * @param code
     * @return List<TblMachineCode>
     */
    List<TblMachineCode> queryNotUsedByCode(String code);

    /**
     * 批量保存机器码
     * (这里描述这个方法适用条件 – 可选)
     * @param machineCodes
     * @exception
     * @since 1.0.0
     */
    void saveMachineCodes(List<TblMachineCode> machineCodes);

    /**
     * 查询当前最大机器码
     * (这里描述这个方法适用条件 – 可选)
     * @return
     * @exception
     * @since 1.0.0
     */
    Integer queryMaxCode();
}