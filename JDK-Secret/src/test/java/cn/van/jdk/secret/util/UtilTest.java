package cn.van.jdk.secret.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        logger.info("加密后的密文：[{}]", cipherString);

        // 输出解密后的明文
        String plainStr = AESUtil.decrypt(cipherString, key);
        logger.info("解密后的明文：[{}]", plainStr);
    }


}
