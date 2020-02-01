package cn.van.jdk.five.generic.bigdecimal;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: BigDecimalTest
 *
 * @author: Van
 * Date:     2019-12-14 22:15
 * Description: 泛型方法出现在泛型类中
 * Version： V1.0
 */
public class BigDecimalTest {

    @Test
    public void countDemo() {
        System.out.println(0.06+0.01);
        System.out.println(1.0-0.42);
        System.out.println(4.015*100);
        System.out.println(303.1/1000);
    }

    @Test
    public void bigDecimalUtilTest(){
        // 0.06
        System.out.println(BigDecimalUtil.add(0.05, 0.01));
        // 0.58
        System.out.println(BigDecimalUtil.subtract(1.0, 0.42));
        // 401.5
        System.out.println(BigDecimalUtil.multiply(4.015, 100));
        // 1.233
        System.out.println(BigDecimalUtil.div(123.3, 100));
        // 4.02
        System.out.println(BigDecimalUtil.divide(4.015, 2));
    }

    @Test
    public void compareDecimal() {
        BigDecimal v1 = new BigDecimal("1.2");
        BigDecimal v2 = new BigDecimal("1.20");
        // 0
        System.out.println(v1.compareTo(v2));
        // false
        System.out.println(v1.equals(v2));
    }

    @Test
    public void precisionLose() {
        BigDecimal a = new BigDecimal(1.01);
        BigDecimal b = new BigDecimal(1.02);
        BigDecimal c = new BigDecimal("1.01");
        BigDecimal d = new BigDecimal("1.02");
        // 2.0300000000000000266453525910037569701671600341796875
        System.out.println(a.add(b));
        // 2.03
        System.out.println(c.add(d));
    }
}
