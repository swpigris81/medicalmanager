
/**
 * 系统项目名称
 * com.mfhcd.ipay.ipaypresystem.http.client
 * HttpClient.java
 * 
 * 2015年10月14日-下午5:33:24
 * 2015
 * 
 */
 
package com.medical.manager.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


/**
 * 
 * HttpClient Http请求客户端，使用Apache的HttpClient工具
 * 
 * 2015年10月14日 下午5:33:24
 * 
 * @version 1.0.0
 * 
 */
@Service("httpClient")
public class HttpClient{
    private Logger logger = Logger.getLogger(HttpClient.class);
    private org.apache.commons.httpclient.HttpClient httpClient;
    private PostMethod postMethod;
    private Long contentLength;
    private String contentType;
    private NameValuePair[] postData;
    
    /**
     * connect 连接指定服务器URL
     * (这里描述这个方法适用条件 – 可选)
     * @param httpRequestUrl 服务器URL 
     * @exception 
     * @since  1.0.0
     */
    public void connect(String httpRequestUrl){
        logger.info("HttpClient客户端连接服务器：" + httpRequestUrl);
        if(httpClient == null){
            httpClient = new org.apache.commons.httpclient.HttpClient();
        }
        postMethod = new PostMethod(httpRequestUrl);
        if(contentType != null && !"".equals(contentType)){
            postMethod.setRequestHeader("Content-Type", contentType);
        }
    }
    
    /**
     * sendFile 上传文件
     * (这里描述这个方法适用条件 – 可选)
     * @param file 文件
     * @param params 其他参数
     * @return String
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    public String sendFile(File file, String fileParamName, Map<String, String> params) throws Exception{
        List<Part> partList = new ArrayList<Part>();
        if(params != null && !params.isEmpty()){
            Set<String> keySet = params.keySet();
            for(String key : keySet){
                partList.add(new StringPart(key, params.get(key)));
            }
        }
        if(file != null && file.exists()){
            try{
                partList.add(new FilePart(fileParamName, file));
                Part[] parts = partList.toArray(new Part[]{});
                postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
                int status = httpClient.executeMethod(postMethod);
                String responseMsg = postMethod.getResponseBodyAsString();
                return responseMsg;
            }catch(Exception e){
                throw e;
            }
        }
        return null;
    }
    /**
     * sendPost 发送HTTP请求
     * (这里描述这个方法适用条件 – 可选)
     * @param params 请求参数
     * @return String
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    public String sendPost(Map<String, String> params) throws Exception{
        if(params != null && !params.isEmpty()){
            postData = new NameValuePair[params.size()];
            Set<String> requestSet = params.keySet();
            int i=0;
            for(String key : requestSet){
                postData[i] = new NameValuePair(key, params.get(key));
                i++;
            }
        }
        if(postData != null){
            postMethod.setRequestBody(postData);
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
        int statusCode = httpClient.executeMethod(postMethod);
//        byte[] responseBody = postMethod.getResponseBody();
        String responseMsg = postMethod.getResponseBodyAsString();
//        String responseMsg = new String(responseBody, "GBK");
        logger.info("HttpClient客户端交易结果响应码：" + statusCode + ", 交易结果消息：" + responseMsg);
        postMethod.releaseConnection();
        return responseMsg;
    }
    
    /**
     * close 重置HttpClient对象
     * (这里描述这个方法适用条件 – 可选)
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    public void close() throws Exception {
        postMethod.releaseConnection();
        if(httpClient != null){
            httpClient = null;
        }
        postMethod = null;
        contentLength = null;
        contentType = null;
    }
    
    public Long getContentLength() {
        return contentLength;
    }
    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public static void main(String[] args) {
        String smsCode = "";
        HttpClient httpClient = new HttpClient();
        httpClient.connect("http://222.73.117.158/msg/HttpBatchSendSM");
        Map<String, String> paramMap = new LinkedHashMap<String, String>();
        paramMap.put("account", "Dlzd666");
        paramMap.put("pswd", "Dlzd666666");
        paramMap.put("mobile", "18583855089");
        paramMap.put("msg", "为了更方便为你服务，使用智能急救系统，你可以随时知道你爸妈安全状态，请免费下载安装地址：http://500v.com/xz.htm [智能急救]退订TD");
        paramMap.put("needstatus", "false");
        //paramMap.put("product", "");
        //paramMap.put("extno", "");
        try {
            httpClient.sendPost(paramMap);
            System.out.println("短信发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("短信发送失败！" + e.getMessage());
        }
    }
}
