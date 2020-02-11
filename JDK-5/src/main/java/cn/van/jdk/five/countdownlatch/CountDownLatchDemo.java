package cn.van.jdk.five.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: CountDownLatchDemo
 *
 * @author: Van
 * Date:     2020-02-11 20:09
 * Description: 以买菜为例
 * Version： V1.0
 */
@Slf4j
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        // 夫妻分头去买菜，夫妻买蔬菜(需要5秒)，丈夫卖肉(需要3秒)
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(new HusbandTask(countDownLatch));
        executor.execute(new WifeTask(countDownLatch));
        // 挂起任务，等菜买好了再一起回家
        countDownLatch.await();
        log.info("菜买好了，夫妻双双把家还，买菜合计耗时:{}毫秒",(System.currentTimeMillis()-startTime));
    }

}