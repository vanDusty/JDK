package cn.van.jdk.juc.countdownlatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: HusbandTask
 *
 * @author: Van
 * Date:     2020-02-11 20:46
 * Description: 丈夫买肉
 * Version： V1.0
 */
public class HusbandTask implements Runnable {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private CountDownLatch countDownLatch;

    public HusbandTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            // 买肉需要3秒
            TimeUnit.SECONDS.sleep(3);
            logger.info("丈夫肉买好了");
        } catch (InterruptedException e) {
            logger.info("丈夫肉没买到");
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
