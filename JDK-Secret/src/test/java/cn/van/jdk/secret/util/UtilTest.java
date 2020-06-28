package cn.van.jdk.secret.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UtilTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void aesTest() throws Exception {
        // 原文内容
        String content = "你好，我是要发送的数据";
        // AES加密/解密用的原始密码
        String key = "secret";

        // 输出加密后的密文
        String cipherString = AESUtil.encrypt(content, key);
        logger.info("AES 加密后的密文：[{}]", cipherString);

        // 输出解密后的明文
        String plainStr = AESUtil.decrypt(cipherString, key);
        logger.info("AES 解密后的明文：[{}]", plainStr);
    }

    @Test
    public void rsaTest() throws Exception {
        //生成公钥和私钥
        Map<Integer, String> keyMap = RSAUtil.genKeyPair();
        logger.info("公钥:[{}]", keyMap.get(0));
        logger.info("私钥:[{}]", keyMap.get(1));

        // 要加密的数据
        String message = "你好，我是要发送的数据";

        // 使用公钥加密
        String messageEn = RSAUtil.encrypt(message, keyMap.get(0));
        logger.info("RSA 加密后的密文：[{}]", messageEn);

        // 使用私钥解密
        String messageDe = RSAUtil.decrypt(messageEn, keyMap.get(1));
        logger.info("RSA 解密后的明文：[{}]",messageDe);
    }


}
