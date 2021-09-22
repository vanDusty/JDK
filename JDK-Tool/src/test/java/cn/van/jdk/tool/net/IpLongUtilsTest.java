package cn.van.jdk.tool.net;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpLongUtilsTest {
    protected Logger logger= LoggerFactory.getLogger(getClass());

    /**
     * 返回InetAddress
     * @throws Exception
     */
    @Test
    public void testGetLocalInetAddress(){
        logger.info("IP [192.168.0.1] 转换为:[{}]",IpLongUtils.ip2Long("192.168.0.1"));
        logger.info("转换回来:[{}]",IpLongUtils.long2Ip(3232235521L));
        logger.info("IP [10.0.0.1] 转换为:[{}]",IpLongUtils.ip2Long("10.0.0.1"));
    }


}