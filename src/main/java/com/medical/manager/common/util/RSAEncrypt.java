package com.medical.manager.common.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public class RSAEncrypt {

    private static Logger logger = Logger.getLogger(RSAEncrypt.class);
    private static final Map<String, String> paramMap = new HashMap<String, String>() {
        private static final long serialVersionUID = 2271623527630152208L;
        {
            put("key_algorithm", "RSA");
            put("key1", "RSAPublicKey");
            put("key2", "RSAPrivateKey");
        }
    };

    private static final Map<String, String> keyMap = new HashMap<String, String>() {
        private static final long serialVersionUID = -5522375634307818191L;
        {
            put(paramMap.get("key2"),
                    "30820153020100300d06092a864886f70d01010105000482013d308201390201000241008266252a155925ed23dbe985c3601d7a7087fc7e3c34670aedf6b50df89b1b9f23762536ea84b73f19ce2aafbe19a43b78081cfaacdd8ab21f03217a4c87f12502030100010240484ec5eaf91d8f17599c17791226d911cd16cb1f60d91a0e8878165be1f078b6c7931095fd6b674e470b4e971e85c217cf75c208e428e5180eb42d7a4f0cb61d022100fd50db566bec9202565ac232333629983f36860d7b50c774a52ce5e51aa810e702210083c7dd649ca66254195c2a0d923c9b7979183f9085f6d79eb09af3f587b5d0130220727907a5963fe1fb8c6a9737784d119eeb7208422f67e1baa4aa1579e301f02102201adc2c060bdfe5feda446cd5e34eb329e942c602012c34fac5686ffd45b20b3d022000f8a9d44cb36ce09a7e8145815fea9e8c38bd176cb301eb4af466c1b0975b09");
            put(paramMap.get("key1"),
                    "305c300d06092a864886f70d0101010500034b0030480241008266252a155925ed23dbe985c3601d7a7087fc7e3c34670aedf6b50df89b1b9f23762536ea84b73f19ce2aafbe19a43b78081cfaacdd8ab21f03217a4c87f1250203010001");
        }
    };

    /**
     * 
     * @param k
     * @param data
     * @param encrypt
     *            1 加密 0解密
     * @return
     * @throws NoSuchPaddingException
     * @throws Exception
     */
    private static byte[] handleData(Key k, byte[] data, int encrypt) throws Exception {

        if (k != null) {

            Cipher cipher = Cipher.getInstance("RSA");

            if (encrypt == 1) {
                cipher.init(Cipher.ENCRYPT_MODE, k);
                byte[] resultBytes = null;
                for (int i = 0; i < data.length; i += 48) {
                    byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 48));
                    resultBytes = ArrayUtils.addAll(resultBytes, doFinal);
                }
                return resultBytes;
            } else if (encrypt == 0) {
                cipher.init(Cipher.DECRYPT_MODE, k);
                byte[] resultBytes = null;
                for (int i = 0; i < data.length; i += 64) {
                    byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 64));
                    resultBytes = ArrayUtils.addAll(resultBytes, doFinal);
                }
                return resultBytes;
            } else {
                System.out.println("参数必须为: 1 加密 0解密");
            }
        }
        return null;
    }

    private static String byte2String(byte[] b) {
        StringBuffer sb = new StringBuffer();
        String tmp = "";
        for (int i = 0; i < b.length; i++) {
            tmp = Integer.toHexString(b[i] & 0XFF);
            if (tmp.length() == 1) {
                sb.append("0" + tmp);
            } else {
                sb.append(tmp);
            }

        }
        return sb.toString();
    }

    // 字符串转二进制
    private static byte[] string2Byte(String str) {
        if (str == null) {
            return null;
        }

        str = str.trim();
        int len = str.length();

        if (len == 0 || len % 2 == 1) {
            return null;
        }

        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 加密
     * <p>
     * <strong>Description:</strong> encoder
     * </p>
     * 
     * @param msg
     * @return
     * @update 日期: 2014-12-30
     */
    public static String encoder(String msg) {
        try {
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(string2Byte(keyMap.get(paramMap.get("key2"))));
            KeyFactory keyFactory = KeyFactory.getInstance(paramMap.get("key_algorithm"));
            Key prikey = keyFactory.generatePrivate(pkcs8KeySpec);
            
            byte[] result = handleData(prikey, msg.getBytes("UTF-8"), 1);
            return byte2String(result);
        } catch (Exception e) {
            logger.error("加密失败" + e.getMessage(), e);
        }
        return "ERROR";
    }

    /**
     * 解密
     * <p>
     * <strong>Description:</strong> decoder
     * </p>
     * 
     * @param msg
     * @return
     * @update 日期: 2014-12-30
     */
    public static String decoder(String msg) {
        try {
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(string2Byte(keyMap.get(paramMap.get("key1"))));
            KeyFactory keyFactory = KeyFactory.getInstance(paramMap.get("key_algorithm"));
            Key pubkey = keyFactory.generatePublic(x509KeySpec);

            byte[] result = handleData(pubkey, string2Byte(msg), 0);
            return new String(result, "utf-8");
        } catch (Exception e) {
            logger.error("解密失败" + e.getMessage(), e);
        }
        return "ERROR";
    }

    public static void main(String[] args) throws Exception {
        System.out
                .println(encoder("阿里斯顿就阿萨德空间了解卡上大量看似简单扣篮对决啊看来大家卡洛斯多久啊看来圣诞节阿克苏加大了是大量看似大家阿拉看似大家卡洛斯就的卡集散地阿克苏就的卡圣诞节啊看似大家卡萨帝景卡上就的卡司机达拉斯大家卡萨大家卡洛斯的就蓬松的品啊水平低哦爬上爬的答对啦达斯达克啊圣诞节kajsasld' sd sdsd sdasd; 1231234354545 }{Pdasd sad 德拉克"));
        System.out
                .println(decoder("76bd7a4e887857d5868afabf1a364c7572c940f4f68eb5db12624d6534c32e07c4798c0e490cbd23a5c57aa9237a795b6ca73c20837d5d776de6adbf98d3845360d6cf0909560aa9fc21f4ef8e3ffe5480b4b971bf5d8e7da7fdfe9f35eb8a708ed0659a4b90be4b566215b28422ce0189aefab39358eec29e515708d433cb336020b87e3b5fe7d93c2658cd6825b3c244e897a825975f850a4cb35bf3d869bb482d3de8e44f56f4e6f9584bf6ecd770c64a91d29abc2a4940fd55494955855b3eba25a177a792b2f6cec534afaac48a2db98c21cfe769550babe6ad1d7bc4cf3d52bb84ee339dcd20100f1d7691a643f181de010e6cbfff004d77a2b602dc020fbcbc8b24fcd3d0f7345aa0a4d158dad3788c84a41ee9dcff8133974a6669de5baaa3949d344c43d2e06f6a464317475b5abf834411c2f89808c1d7d2540e781a11407f8860cd0d18bae27c99f50d783ce13f209b89c2accb1aa2868234472516c9a25e85a277f2ac274df8c4f84e48db59e065f6c6b2879c52346f4d47126e08c8697a0640e850d27de6707730ddac38ac04f089177ec4cf37946f3c98ebfcd44da1bf93511141c284b5830475224b685313507c3856c02686a85a71f9e4c0647634115bff5af3db7a22e2af54f136a45a7ce7ba0d8acb841443eb538e420c46bd2d5553e7288df7503744ec792a4a691e1f3f252643a3178b91f6653185800e5d03a85e58e88bb29c4c1bcd1662a4d58e154bf92d2d7104393a2002ebe84c00ddd5175582543f0a57d0213cc4fefb9dc7b2054a29f58b7ac4312092934674780ec5e79910d49aa695389223a1db7adaf68fc71fe849293da40291eb9339724566073736e0f841b0fba0600b10559803efca0a964925c4cbd051d128824dc6"));

    }

}
