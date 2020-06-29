package cn.van.jdk.juc.cyclicbarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: FarmerPlayer1
 *
 * @author: Van
 * Date:     2020-02-13 20:09
 * Description: 贫民玩家1
 * Version： V1.0
 */
public class FarmerPlayer1 implements Runnable {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private CyclicBarrier cyclicBarrier;

    public FarmerPlayer1(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            // 贫民玩家1玩家初始化游戏需要3秒
            TimeUnit.SECONDS.sleep(3);
            logger.info("【贫民玩家1】进入游戏");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            logger.info("【贫民玩家1】进入游戏失败");
        }
    }
}