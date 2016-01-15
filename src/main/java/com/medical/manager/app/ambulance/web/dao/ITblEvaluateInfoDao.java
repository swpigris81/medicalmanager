package com.medical.manager.app.ambulance.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.medical.manager.app.ambulance.web.model.TblEvaluateInfo;
import com.medical.manager.app.ambulance.web.query.TblEvaluateInfoExample;
import com.medical.manager.app.center.web.vo.EvaluationInfo;

@Repository("tblEvaluateInfoDao")
public interface ITblEvaluateInfoDao {
    int countByExample(TblEvaluateInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblEvaluateInfo record);

    int insertSelective(TblEvaluateInfo record);

    List<TblEvaluateInfo> selectByExample(TblEvaluateInfoExample example);

    TblEvaluateInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblEvaluateInfo record);

    int updateByPrimaryKey(TblEvaluateInfo record);

    /**
     * 查询评论总数以及评价平均值
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @return
     * @exception
     * @since 1.0.0
     */
    Map<String, String> countEvaluate(Long cusId);

    /**
     * 查询评论列表
     * (这里描述这个方法适用条件 – 可选)
     * @param cusId
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblEvaluateInfo> queryEvaluateList(Long cusId);
    /**
     * <p>Discription:[120中心评价查询]</p>
     * @param type 类型
     * @param beginDate 评价时间
     * @param endDate 评价时间
     * @return
     * @author: Jason
     * @update: 2015年12月17日 Jason[变更描述]
     */
    List<EvaluationInfo> queryEvaluateInfoList(Map paramMap);
}