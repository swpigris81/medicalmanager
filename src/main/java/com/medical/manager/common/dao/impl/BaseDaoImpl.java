
/**
 * 系统项目名称
 * com.supermarket.common.dao.impl
 * BaseDaoImpl.java
 * 
 * 2015年7月7日-下午2:01:44
 * 2015版权所有
 * 
 */
 
package com.medical.manager.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.medical.manager.common.dao.IBaseDao;


/**
 * @description 基础数据
 * 
 * @time 2015年7月7日 下午2:01:44
 * @author Jason
 * @version 1.0.0
 * 
 */
@Repository("baseDao")
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements IBaseDao<T> {
    private Logger logger = Logger.getLogger(BaseDaoImpl.class);
    /**
     * @description queryWithPage 分页查询公共接口
     * @param paramMap 查询参数
     * @param pageNum 当前第几页
     * @param pageSize 每页条数
     * @param namespace 查询SQL_ID
     * @return List<T>
     * @exception 
     * @author Jason
     * @since  1.0.0
     */
    @Override
    public List<T> queryWithPage(Map paramMap, Integer pageNum, Integer pageSize, String namespace) {
        logger.info("分页查询：" + namespace + "，当前查询第：" + pageNum + " 页，每页：" + pageSize + "：条数据");
        SqlSession sqlSession = getSqlSession();
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = sqlSession.selectList(namespace, paramMap);
        return list;
    }
}
