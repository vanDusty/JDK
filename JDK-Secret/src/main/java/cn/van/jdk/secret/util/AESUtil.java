package cn.van.jdk.secret.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.SecureRandom;

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
        byte [] plainBytes = plainStr.getBytes();
        byte[] keyBytes = keyStr.getBytes();
        // 加密数据, 返回密文
        byte[] cipherBytes = encrypt(plainBytes, keyBytes);
        return bytesToHex(cipherBytes);
    }
    public static byte[] encrypt(byte[] plainBytes, byte[] key) throws Exception {
        // 生成密钥对象
        SecretKey secKey = generateKey(key);

        // 获取 AES 密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化密码器（加密模型）
        cipher.init(Cipher.ENCRYPT_MODE, secKey);

        // 加密数据, 返回密文

        return cipher.doFinal(plainBytes);
    }

    /**
     * 数据解密: 密文 -> 明文
     */
    public static String decrypt(String cipherStr, String keyStr) throws Exception {
        byte[] cipherBytes = hexToByteArray(cipherStr);
        byte[] keyBytes = keyStr.getBytes();
        byte[] plainBytes = decrypt(cipherBytes, keyBytes);
        return new String(plainBytes);
    }
    public static byte[] decrypt(byte[] cipherBytes, byte[] key) throws Exception {
        // 生成密钥对象
        SecretKey secKey = generateKey(key);

        // 获取 AES 密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化密码器（解密模型）
        cipher.init(Cipher.DECRYPT_MODE, secKey);

        // 解密数据, 返回明文
        return cipher.doFinal(cipherBytes);
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
    public static String bytesToHex(byte[] bytes) {
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
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    /**
     * 16进制转byte数组
     *
     * @param inHex 16进制字符串
     * @return byte数组
     */
    public static byte[] hexToByteArray(String inHex) {
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

    /**
     * 加密文件: 明文输入 -> 密文输出
     */
    public static void encryptFile(File plainIn, File cipherOut, byte[] key) throws Exception {
        aesFile(plainIn, cipherOut, key, true);
    }

    /**
     * 解密文件: 密文输入 -> 明文输出
     */
    public static void decryptFile(File cipherIn, File plainOut, byte[] key) throws Exception {
        aesFile(plainOut, cipherIn, key, false);
    }

    /**
     * AES 加密/解密文件
     */
    private static void aesFile(File plainFile, File cipherFile, byte[] key, boolean isEncrypt) throws Exception {
        // 获取 AES 密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 生成密钥对象
        SecretKey secKey = generateKey(key);
        // 初始化密码器
        cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, secKey);

        // 加密/解密数据
        InputStream in = null;
        OutputStream out = null;

        try {
            if (isEncrypt) {
                // 加密: 明文文件为输入, 密文文件为输出
                in = new FileInputStream(plainFile);
                out = new FileOutputStream(cipherFile);
            } else {
                // 解密: 密文文件为输入, 明文文件为输出
                in = new FileInputStream(cipherFile);
                out = new FileOutputStream(plainFile);
            }

            byte[] buf = new byte[1024];
            int len = -1;

            // 循环读取数据 加密/解密
            while ((len = in.read(buf)) != -1) {
                out.write(cipher.update(buf, 0, len));
            }
            out.write(cipher.doFinal());    // 最后需要收尾

            out.flush();

        } finally {
            close(in);
            close(out);
        }
    }

    private static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // nothing
            }
        }
    }
}
