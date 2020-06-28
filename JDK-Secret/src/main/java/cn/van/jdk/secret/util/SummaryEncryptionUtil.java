package cn.van.jdk.secret.util;

import java.security.MessageDigest;

/**
 * Copyright (C), 2015-2019, 风尘博客
 * 公众号 : 风尘博客
 * FileName: SummaryEncryptionUtil
 *
 * @author: Van
 * Date:     2019-10-16 20:12
 * Description: 摘要算法加密工具类
 * Version： V1.0
 */
public class SummaryEncryptionUtil {
    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA1";
    private static final String SHA_256 = "SHA-256";
    /**
     * 用来将字节转换成 16 进制表示的字符
     */
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * MD5 加密
     *
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String encodeWithMD5(String str) {
        return encode(MD5, str);
    }

    /**
     * SHA1 加密
     *
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String encodeWithSHA1(String str) {
        return encode(SHA1, str);
    }

    /**
     * SHA-256 加密
     *
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String encodeWithSHA256(String str) {
        return encode(SHA_256, str);
    }

    /**
     * 通过加密算法加密字符串
     */
    private static String encode(String algorithm, String str) {
        if (str == null) {
            return null;
        }
        try {
            // 生成一个指定算法加密计算摘要
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}
