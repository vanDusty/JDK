package cn.van.jdk.juc.countdownlatch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: WifeTask
 *
 * @author: Van
 * Date:     2020-02-11 20:46
 * Description: 妻子买蔬菜
 * Version： V1.0
 */
public class WifeTask implements Runnable {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private CountDownLatch countDownLatch;

    public WifeTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            // 买蔬菜需要5秒
            TimeUnit.SECONDS.sleep(5);
            logger.info("妻子蔬菜买好了");
        } catch (InterruptedException e) {
            logger.info("妻子蔬菜没买到");
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
