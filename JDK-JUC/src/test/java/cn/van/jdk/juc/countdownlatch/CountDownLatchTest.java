package cn.van.jdk.juc.countdownlatch;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: CountDownLatchTest
 *
 * @author: Van
 * Date:     2020-02-11 20:09
 * Description: 以买菜为例
 * Version： V1.0
 */
public class CountDownLatchTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void countDownLatchDemo() throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        logger.info("夫妻分头去买菜");
        // 夫妻分头去买菜，夫妻买蔬菜(需要5秒)，丈夫卖肉(需要3秒)
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(new HusbandTask(countDownLatch));
        executor.execute(new WifeTask(countDownLatch));
        // 挂起任务，等菜买好了再一起回家
        countDownLatch.await();
        logger.info("菜买好了，夫妻双双把家还，买菜合计耗时:{}毫秒", (System.currentTimeMillis() - startTime));
    }
}