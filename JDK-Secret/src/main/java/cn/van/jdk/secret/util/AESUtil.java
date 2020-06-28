package cn.van.jdk.secret.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: AESUtil
 *
 * @author: Van
 * Date:     2020-06-20 23:17
 * Description: AES 加密工具
 * Version： V1.0
 */
public class AESUtil {
    /**
     * 密钥长度: 128, 192 or 256
     */
    private static final int KEY_SIZE = 128;

    /**
     * 加密/解密算法名称
     */
    private static final String ALGORITHM = "AES";

    /**
     * 随机数生成器（RNG）算法名称
     */
    private static final String RNG_ALGORITHM = "SHA1PRNG";

    /**
     * 数据加密: 明文 -> 密文
     */

    public static String encrypt(String plainStr, String keyStr) throws Exception {
        byte[] plainBytes = plainStr.getBytes();
        byte[] keyBytes = keyStr.getBytes();
        // 生成密钥对象
        SecretKey secKey = generateKey(keyBytes);
        // 获取 AES 密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化密码器（加密模型）
        cipher.init(Cipher.ENCRYPT_MODE, secKey);
        // 加密数据, 返回密文
        byte[] cipherBytes = cipher.doFinal(plainBytes);
        return bytesToHex(cipherBytes);
    }

    /**
     * 数据解密: 密文 -> 明文
     */
    public static String decrypt(String cipherStr, String keyStr) throws Exception {
        byte[] cipherBytes = hexToByteArray(cipherStr);
        byte[] keyBytes = keyStr.getBytes();
        // 生成密钥对象
        SecretKey secKey = generateKey(keyBytes);
        // 获取 AES 密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化密码器（解密模型）
        cipher.init(Cipher.DECRYPT_MODE, secKey);
        // 解密数据, 返回明文
        byte[] plainBytes = cipher.doFinal(cipherBytes);
        return new String(plainBytes);
    }

    /**
     * 生成密钥对象
     *
     * @param key byte[] 类型参数
     * @return AES密钥对象
     */
    private static SecretKey generateKey(byte[] key) throws Exception {
        // 创建安全随机数生成器
        SecureRandom random = SecureRandom.getInstance(RNG_ALGORITHM);
        // 设置 密钥key的字节数组 作为安全随机数生成器的种子
        random.setSeed(key);

        // 创建 AES算法生成器
        KeyGenerator gen = KeyGenerator.getInstance(ALGORITHM);
        // 初始化算法生成器
        gen.init(KEY_SIZE, random);

        // 生成 AES密钥对象
        return gen.generateKey();
    }

    /**
     * byte数组转16进制
     *
     * @param bytes byte数组
     * @return 返回16进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 16进制转byte
     *
     * @param inHex 16进制字符串
     * @return byte
     */
    private static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    /**
     * 16进制转byte数组
     *
     * @param inHex 16进制字符串
     * @return byte数组
     */
    private static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }
}
