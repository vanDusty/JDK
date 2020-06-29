package cn.van.jdk.juc.cyclicbarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: LandlordPlayer
 *
 * @author: Van
 * Date:     2020-02-13 20:09
 * Description: 地主玩家
 * Version： V1.0
 */
public class LandlordPlayer implements Runnable {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private CyclicBarrier cyclicBarrier;

    public LandlordPlayer(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            // 地主玩家初始化游戏需要2秒
            TimeUnit.SECONDS.sleep(2);
            logger.info("【地主玩家】进入游戏");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            logger.info("【地主玩家】进入游戏失败！");
        }
    }
}