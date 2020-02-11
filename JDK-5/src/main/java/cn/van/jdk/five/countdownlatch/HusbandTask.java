package cn.van.jdk.five.countdownlatch;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class HusbandTask implements Runnable{

    private CountDownLatch countDownLatch;

    public HusbandTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }


    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
            log.info("丈夫肉买好了");
        } catch (InterruptedException e) {
            log.info("丈夫肉没买到");
        } finally {
            if(countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
