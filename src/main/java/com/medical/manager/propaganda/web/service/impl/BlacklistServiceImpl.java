
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.service.impl
 * BlacklistServiceImpl.java
 * 
 * 2015年12月4日-下午2:22:14
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.propaganda.web.dao.IPropaBlacklistDao;
import com.medical.manager.propaganda.web.model.TblPropaBlacklist;
import com.medical.manager.propaganda.web.service.IBlacklistService;


/**
 * 
 * BlacklistServiceImpl 黑名单
 * 
 * 2015年12月4日 下午2:22:14
 * 
 * @version 1.0.0
 * 
 */
@Service("blacklistService")
@Transactional
public class BlacklistServiceImpl implements IBlacklistService {
    @Resource
    private IBaseDao<TblPropaBlacklist> baseDao;
    @Resource
    private IPropaBlacklistDao propaBlacklistDao;
    
    @Override
    public List<TblPropaBlacklist> queryWithPaging(Integer pageNum, Integer pageSize, Map paramMap) {
        return baseDao.queryWithPage(paramMap, pageNum, pageSize, "findBlacklist");
    }

    @Override
    public void doSaveNewBlacklist(TblPropaBlacklist blacklist) {
        propaBlacklistDao.addNewBlacklist(blacklist);
    }

    @Override
    public void doUpdateBlacklist(TblPropaBlacklist blacklist) {
        propaBlacklistDao.updateBlacklist(blacklist);
    }

    @Override
    public void doDeleteBlacklist(List blackIdList) {
        propaBlacklistDao.deleteBlacklist(blackIdList);
    }
}
