
/**
 * 系统项目名称
 * com.medical.manager.app.common.service.impl
 * CommonServiceImpl.java
 * 
 * 2015年12月14日-下午12:52:38
 * 2015
 * 
 */
 
package com.medical.manager.app.common.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.medical.manager.app.common.service.ICommonService;
import com.medical.manager.common.CommonEnums;
import com.medical.manager.common.util.HttpClient;
import com.medical.manager.customer.web.dao.ICusBasicInfoDao;
import com.medical.manager.customer.web.model.CusBasicInfo;


/**
 * 
 * CommonServiceImpl 公共服务类
 * 
 * 2015年12月14日 下午12:52:38
 * 
 * @version 1.0.0
 * 
 */
@Service("commonService")
public class CommonServiceImpl implements ICommonService {
    private Logger logger = Logger.getLogger(CommonServiceImpl.class);
    @Resource
    private ICusBasicInfoDao cusBasicInfoDao;
    /**
     * 短信服务器
     */
    @Value("${sms.server.url}")
    private String smsUrl;
    /**
     * 短信账号
     */
    @Value("${sms.server.account}")
    private String accountNo;
    /**
     * 短信账号密码
     */
    @Value("${sms.server.password}")
    private String password;
    /**
     * 广告短信模板
     */
    @Value("${sms.server.message}")
    private String smsMessage;

    @Override
    public boolean isRegistered(String mobileNo) {
        CusBasicInfo custInfo = cusBasicInfoDao.queryByMobileNo(mobileNo);
        if(custInfo == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String sendSmsCode(String mobileNo) {
        CusBasicInfo custInfo = cusBasicInfoDao.queryByMobileNo(mobileNo);
        if(custInfo == null || CommonEnums.YesOrNop.NO.id.equals(custInfo.getHasSent())){
            HttpClient httpClient = new HttpClient();
            httpClient.connect(smsUrl);
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("account", accountNo);
            paramMap.put("pswd", password);
            paramMap.put("mobile", mobileNo);
            paramMap.put("msg", smsMessage);
            paramMap.put("needstatus", "false");
            try {
                httpClient.sendPost(paramMap);
                logger.info("短信发送成功！");
                if(custInfo == null){
                    custInfo = new CusBasicInfo();
                    custInfo.setHasSent(CommonEnums.YesOrNop.YES.id);
                    custInfo.setPhone(mobileNo);
                    cusBasicInfoDao.insert(custInfo);
                }else{
                    custInfo.setHasSent(CommonEnums.YesOrNop.YES.id);
                    cusBasicInfoDao.update(custInfo);
                }
                
                return smsMessage;
            } catch (Exception e) {
                logger.error("短信发送失败！" + e.getMessage(), e);
            }
        }
        return null;
    }
    
}
