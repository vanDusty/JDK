package cn.van.jdk.tool.math;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: BigDecimalTest
 *
 * @author: Van
 * Date:     2019-12-14 22:15
 * Description: 科学计算工具类 测试
 * Version： V1.0
 */
public class BigDecimalTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void add() {
        double v1 = BigDecimalUtil.add(1.1, 1.1);
        logger.info("result:{}", v1);

        BigDecimal v2 = BigDecimalUtil.add(1, 1.1, 1.2);
        logger.info("result:{}", v2);
    }


    @Test
    public void sub() {
        double v1 = BigDecimalUtil.sub(1.0, 1.1);
        logger.info("result:{}", v1);

        BigDecimal v2 = BigDecimalUtil.sub(1, 1.1, 1.2);
        logger.info("result:{}", v2);
    }

    @Test
    public void mul() {
        double v1 = BigDecimalUtil.mul(1.1, 1.1);
        logger.info("result:{}", v1);

        BigDecimal v2 = BigDecimalUtil.mul(1.1, 1.1, 2);
        logger.info("result:{}", v2);
    }

    @Test
    public void div() {
        double v1 = BigDecimalUtil.div(2.25, 1.5);
        logger.info("result:{}", v1);

        double v2 = BigDecimalUtil.div(1.5, 2.25);
        logger.info("result:{}", v2);
    }

    @Test
    public void round() {
        double value = 0.646464;
        double roundHalfUp = BigDecimalUtil.roundHalfUp(value, 3);
        logger.info("result:{}", roundHalfUp);

        double roundUp = BigDecimalUtil.roundUp(value, 3);
        logger.info("result:{}", roundUp);

        double roundDown = BigDecimalUtil.roundDown(value, 3);
        logger.info("result:{}", roundDown);
    }

    @Test
    public void divWithScale() {
        double v1 = 1.5;
        double v2 = 2.25;
        double divOfUp = BigDecimalUtil.divOfUp(v1,v2, 3);
        logger.info("result:{}", divOfUp);

        double divOfDown = BigDecimalUtil.divOfDown(v1,v2, 3);
        logger.info("result:{}", divOfDown);
    }


    @Test
    public void movePoint() {
        double value = 1000.01;
        double roundHalfUp = BigDecimalUtil.movePointRight(value, 3);
        logger.info("result:{}", roundHalfUp);

        double roundUp = BigDecimalUtil.movePointLeft(value, 3);
        logger.info("result:{}", roundUp);
    }

    /**
     * 大小比较
     */
    @Test
    public void compareDecimal() {
        BigDecimal v1 = BigDecimal.valueOf(1.21);
        BigDecimal v2 = BigDecimal.valueOf(1.22);
        BigDecimal v3 = BigDecimal.valueOf(1.22);
        // 0
        logger.info("result:{}",v1.compareTo(v2));
        logger.info("result:{}",v2.compareTo(v1));
        logger.info("result:{}",v2.compareTo(v3));
    }

    /**
     * 精度丢失
     */
    @Test
    public void precisionLose() {
        BigDecimal v1 = new BigDecimal(1.01);
        BigDecimal v2 = new BigDecimal(1.02);
        BigDecimal v3 = new BigDecimal("1.01");
        BigDecimal v4 = new BigDecimal("1.02");
        // 2.0300000000000000266453525910037569701671600341796875
        logger.info("result:{}",v1.add(v2));
        // 2.03
        logger.info("result:{}",v3.add(v4));
    }
}
