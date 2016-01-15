
/**
 * 系统项目名称
 * com.medical.manager.app.common.service
 * ICommonService.java
 * 
 * 2015年12月14日-下午12:52:22
 * 2015
 * 
 */
 
package com.medical.manager.app.common.service;


/**
 * 
 * ICommonService
 * 
 * 2015年12月14日 下午12:52:22
 * 
 * @version 1.0.0
 * 
 */

public interface ICommonService {
    /**
     * isRegistered 检查手机号是否已注册
     * (这里描述这个方法适用条件 – 可选)
     * @param mobileNo 手机号
     * @return boolean 是否已注册
     * @exception 
     * @since  1.0.0
     */
    boolean isRegistered(String mobileNo);
    /**
     * sendSmsCode 发送短信验证码
     * (这里描述这个方法适用条件 – 可选)
     * @param mobileNo 手机号
     * @return String 已发送的短信验证码
     * @exception 
     * @since  1.0.0
     */
    public String sendSmsCode(String mobileNo);
    
    
}
