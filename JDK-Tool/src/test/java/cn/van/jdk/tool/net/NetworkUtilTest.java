package cn.van.jdk.tool.net;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public class NetworkUtilTest {
    protected Logger logger= LoggerFactory.getLogger(getClass());

    /**
     * 返回InetAddress
     * @throws Exception
     */
    @Test
    public void testGetLocalInetAddress() throws Exception {

        final InetAddress result = NetworkUtil.getLocalInetAddress();
        logger.info("result:[{}]", result);
    }

    /**
     * 返回本机IP
     */
    @Test
    public void testGetLocalHostAddress() {
        logger.info("result:[{}]", NetworkUtil.getLocalHostAddress());
    }

    /**
     * 返回主机名
     */
    @Test
    public void testGetLocalHostName() {
        logger.info("result:[{}]", NetworkUtil.getLocalHostName());

    }
}
