
/**
 * 系统项目名称
 * com.medical.manager.propaganda.web.service
 * IMessageService.java
 * 
 * 2015年12月4日-下午1:46:12
 * 2015
 * 
 */
 
package com.medical.manager.propaganda.web.service;

import java.util.List;
import java.util.Map;

import com.medical.manager.propaganda.web.model.TblPropaSiteMessage;


/**
 * 
 * IMessageService 站内信
 * 
 * 2015年12月4日 下午1:46:12
 * 
 * @version 1.0.0
 * 
 */
public interface IMessageService {
    /**
     * queryUserWithPaging 分页查询站内信
     * (这里描述这个方法适用条件 – 可选)
     * @param pageNum
     * @param pageSize
     * @param paramMap 查询条件
     * @return List<TblPropaSiteMessage> 站内信
     * @exception 
     * @since  1.0.0
     */
    public List<TblPropaSiteMessage> queryWithPaging(Integer pageNum, Integer pageSize, Map paramMap);
    /**
     * <p>Discription:[修改站内信]</p>
     * @param siteMessage
     * @author: Jason
     * @throws Exception 
     * @update: 2015年12月5日 Jason[变更描述]
     */
    public void doUpdateMessage(TblPropaSiteMessage siteMessage) throws Exception;
    /**
     * <p>Discription:[发送站内信]</p>
     * @param siteMessage
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    public void doSendMessage(TblPropaSiteMessage siteMessage);
    /**
     * <p>Discription:[阅读站内信]</p>
     * @param siteMessage
     * @author: Jason
     * @update: 2015年12月5日 Jason[变更描述]
     */
    public void doReadMessage(TblPropaSiteMessage siteMessage);
    
}
