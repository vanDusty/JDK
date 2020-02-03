package cn.van.jdk.eight.time;

import java.time.Instant;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: InstantDemo
 *
 * @author: Van
 * Date:     2020-02-03 20:46
 * Description: Instant 案例
 * Version： V1.0
 */
public class InstantDemo {

    public static void main(String[] args) {

        // 创建Instant对象
        Instant instant = Instant.now();
        // 通过ofEpochSecond方法创建(第一个参数表示秒，第二个参数表示纳秒)
        Instant another = Instant.ofEpochSecond(365 * 24 * 60, 100);

        // 获取到秒数
        long currentSecond = instant.getEpochSecond();
        System.out.println("获取到秒数：" + currentSecond);

        // 获取到毫秒数
        long currentMilli = instant.toEpochMilli();
        System.out.println("获取到毫秒数：" + currentMilli);
    }
}