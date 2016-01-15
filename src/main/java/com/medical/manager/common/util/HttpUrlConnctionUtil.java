package com.medical.manager.common.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;


/**
 * @description 外部HTTP(s)请求
 * 
 * @time 2015年7月13日 下午3:19:44
 * @author Jason
 * @version 1.0.0
 *
 */
public class HttpUrlConnctionUtil {
    private static final Logger logger = Logger.getLogger(HttpUrlConnctionUtil.class);

    public static String getResposeContent(String urlStr, String method, String postContent) {
        String resposeConent = "";
        try {
            HttpURLConnection cn = null;
            URL url = new URL(urlStr);
            cn = (HttpURLConnection) url.openConnection();
            //cn.addRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            cn.setDoOutput(true);
            cn.setDoInput(true);
            cn.setUseCaches(false);
            if (method.equalsIgnoreCase("GET")) {
                resposeConent = getGetResponseContent(cn);
            }
            if (method.equalsIgnoreCase("POST")) {
                resposeConent = getPostResponseContent(cn, postContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("返回的消息：" + resposeConent);
        return resposeConent;
    }

    private static String getPostResponseContent(HttpURLConnection cn, String postContent) throws IOException {
    	cn.addRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        cn.setRequestMethod("POST");
        OutputStream outputStream = cn.getOutputStream();
        outputStream.write(postContent.toString().getBytes("utf-8"));
        outputStream.flush();
        outputStream.close();

        InputStreamReader inputStream = new InputStreamReader(cn.getInputStream(),"utf-8");
        int i = 0;
        String content = "";
        while ((i = inputStream.read()) != -1) {
            content = content + (char) i;
        }
        inputStream.close();
        return content;
    }

    private static String getGetResponseContent(HttpURLConnection cn) throws IOException {
        cn.setRequestMethod("GET");
        InputStreamReader inputStream = new InputStreamReader(cn.getInputStream(),"utf-8");
        int i = 0;
        String content = "";
        while ((i = inputStream.read()) != -1) {
            content = content + (char) i;
        }

        inputStream.close();
        return content;
    }

    public static void main(String[] args) {
        System.out.println(HttpUrlConnctionUtil.getResposeContent(
                "http://localhost:8080/YeeknetWeChat/interfaceServlet", "POST", 
                "<xml>"
                        + "<ToUserName><![CDATA[oJXLojvSOf1hgphKFSsZHV5Mv_hE]]></ToUserName>"
                        + "<FromUserName><![CDATA[oJXLojutJyoLGGQerDftzTL0_Kfw]]></FromUserName>"
                        + "<CreateTime><![CDATA[13121312]]></CreateTime>" + "<MsgType><![CDATA[event]]></MsgType>"
                        + "<Event><![CDATA[subscribe]]></Event>"
                        + "<EventKey><![CDATA[003003]]></EventKey>" + "<MsgId>1313213</MsgId></xml>"
                        
//                "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>" +
// "<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>" +
// "<CreateTime>1408090606</CreateTime>" +
// "<MsgType><![CDATA[event]]></MsgType>" +
// "<Event><![CDATA[scancode_waitmsg]]></Event>" +
// "<EventKey><![CDATA[6]]></EventKey>" +
// "<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>" +
// "<ScanResult><![CDATA[2]]></ScanResult>" +
// "</ScanCodeInfo>" +
// "</xml>"
        		));
    	//System.out.println(RSAEncrypt.decoder(HttpUrlConnctionUtil.getResposeContent("http://localhost:8080/YeeknetService/StarInfo", "post", "pageSize=十的中文")));
    }
}
