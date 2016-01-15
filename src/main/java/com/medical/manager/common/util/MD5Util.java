package com.medical.manager.common.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class MD5Util {
    private static final Logger logger = Logger.getLogger(MD5Util.class);

    /**
     * @description md5Encode MD5加密
     * @param value
     *            要加密的数据
     * @param key
     *            加密盐值
     * @return
     * @throws Exception
     * @return String
     * @exception
     * @author Jason
     * @since 1.0.0
     */
    public static final String md5Encode(String value, String key) throws Exception {

        if (value == null || value.equals("")) {
            throw new Exception("不能加密空字串！");
        }
        if (key == null || key.equals("")) {
            key = "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] _value = (value + "{" + key + "}").getBytes("UTF-8");
            byte[] result = messageDigest.digest(_value);
            return bytesToHexString(result);
        } catch (Exception e) {
            throw e;
        }
    }

    public static final String md5Encode(String value) {
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            byte[] _value = (value).getBytes("UTF-8");
            byte[] result = messageDigest.digest(_value);
            return bytesToHexString(result);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * Convert hex string to byte[]
     * 
     * @param hexString
     *            the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     * 
     * @param c
     *            char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static void main(String[] args) {
        try {
            String s = MD5Util.md5Encode("812877", "swpigris81");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
