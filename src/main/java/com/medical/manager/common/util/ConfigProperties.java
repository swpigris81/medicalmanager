
/**
 * 系统项目名称
 * com.supermarket.common.util
 * ConfigProperties.java
 * 
 * 2015年7月13日-下午3:11:15
 * 2015版权所有
 * 
 */
 
package com.medical.manager.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;




/**
 * @description 读取配置文件
 * 
 * @time 2015年7月13日 下午3:11:16
 * @author Jason
 * @version 1.0.0
 * 
 */
public class ConfigProperties {
    private static Logger logger = Logger.getLogger(ConfigProperties.class);
    private static Properties props = null;
    private static Map<String, String> cashePropsMap = new HashMap<String, String>();
    /**
     * 取得属性
     * @param key
     * @return
     */
    public static String getProperties(String key) {
        if (props == null)
            systemInit();
        String propsValue = (String) cashePropsMap.get(key);
        if (propsValue == null) {
            propsValue = props.getProperty(key);
            cashePropsMap.put(key, propsValue);
        }
        return propsValue;
    }
    
    /**
     * 取得属性
     * 
     * @param key
     * @return
     */
    public static String getProperties(String key, Map<String, String> regexMap) {
        if (props == null)
            systemInit();
        String propsValue = (String) cashePropsMap.get(key);
        if (propsValue == null) {
            propsValue = props.getProperty(key);
            cashePropsMap.put(key, propsValue);
        }
        if (regexMap != null && regexMap.size() > 0) {
            Set<String> keySet = regexMap.keySet();
            for (String regexKey : keySet) {
                propsValue = propsValue.replaceAll("\\$\\{" + regexKey + "}",
                        regexMap.get(regexKey));
            }
        }
        return propsValue;
    }
    
    private static void loadInitSystemProperties() {
        props = new Properties();
        InputStream in = null;
        try {
            in = ConfigProperties.class.getClassLoader().getResourceAsStream(
                    "config.properties");
            if (in != null) {
                props.load(in);
            } else {
                throw new RuntimeException("无法加载配置文件");
            }

        } catch (Exception ioe) {
            logger.error(ioe.getMessage(),ioe);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe) {
                logger.error("初始化配置文件异常" + ioe.getMessage(),ioe);
            }
        }
    }
    
    /**
     * 清除高速缓存
     */
    public static void cleanCashe() {       
        cashePropsMap.clear();
    } 
    

    public static void systemInit() {
        loadInitSystemProperties();
    }
}
