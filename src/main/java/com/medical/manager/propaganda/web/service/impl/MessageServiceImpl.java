
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.service.impl
 * MessageServiceImpl.java
 * 
 * 2015年12月4日-下午1:46:30
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
import com.medical.manager.propaganda.web.dao.IPropaSiteMessageDao;
import com.medical.manager.propaganda.web.model.TblPropaSiteMessage;
import com.medical.manager.propaganda.web.service.IMessageService;


/**
 * 
 * MessageServiceImpl 站内信服务
 * 
 * 2015年12月4日 下午1:46:30
 * 
 * @version 1.0.0
 * 
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl implements IMessageService {
    @Resource
    private IPropaSiteMessageDao siteMessageDao;
    @Resource
    private IBaseDao<TblPropaSiteMessage> baseDao;
    
    @Override
    public List<TblPropaSiteMessage> queryWithPaging(Integer pageNum, Integer pageSize, Map paramMap) {
        return baseDao.queryWithPage(paramMap, pageNum, pageSize, "findSiteMessage");
    }

    @Override
    public void doUpdateMessage(TblPropaSiteMessage siteMessage) throws Exception {
        TblPropaSiteMessage message = siteMessageDao.findById(siteMessage.getId());
        if(!"00".equals(message.getStatus())){
            throw new Exception("已阅读的信息不能修改！");
        }
        message.setStatus(siteMessage.getStatus());
        siteMessageDao.updateSiteMessage(siteMessage);
    }

    @Override
    public void doSendMessage(TblPropaSiteMessage siteMessage) {
        siteMessageDao.addNewSiteMessage(siteMessage);
    }

    @Override
    public void doReadMessage(TblPropaSiteMessage siteMessage) {
        TblPropaSiteMessage message = siteMessageDao.findById(siteMessage.getId());
        message.setStatus(siteMessage.getStatus());
        siteMessageDao.updateSiteMessage(message);
    }
}
