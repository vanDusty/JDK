package cn.van.jdk.tool.net;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalHostUtilTest {
    protected Logger logger= LoggerFactory.getLogger(getClass());

    /**
     * 返回InetAddress
     * @throws Exception
     */
    @Test
    public void testGetLocalInetAddress() throws Exception {
        logger.info("result:[{}]", LocalHostUtil.getLocalInetAddress());
    }

    /**
     * 返回本机IP
     */
    @Test
    public void testGetLocalHostAddress() {
        logger.info("result:[{}]", LocalHostUtil.getLocalHostAddress());
    }

    /**
     * 返回主机名
     */
    @Test
    public void testGetLocalHostName() {
        logger.info("result:[{}]", LocalHostUtil.getLocalHostName());

    }

    /**
     * 判断操作系统是否是 Windows
     */
    @Test
    public void isWindowsOS() {
        logger.info("result:[{}]", LocalHostUtil.isWindowsOS());
    }

    /**
     * 判断操作系统是否是 MacOS
     */
    @Test
    public void isMacOS() {
        logger.info("result:[{}]", LocalHostUtil.isMacOS());
    }

}
