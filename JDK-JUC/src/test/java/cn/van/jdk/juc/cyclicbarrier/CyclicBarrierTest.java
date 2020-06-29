package cn.van.jdk.juc.cyclicbarrier;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: CyclicBarrierTest
 *
 * @author: Van
 * Date:     2020-02-13 22:19
 * Description: 模拟斗地主
 * Version： V1.0
 */
public class CyclicBarrierTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void cyclicBarrierDemo() throws BrokenBarrierException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        // 4个是因为还有一个主线程也在等待
        logger.info("所有玩家就绪，准备进入游戏");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                logger.info("所有玩家进度条100%，游戏读取中。。。。");
            }
        });
        // 注意：初始化线程数量（如果线程数少于所需线程，则会阻塞）
        Executor executor = Executors.newFixedThreadPool(3);
        executor.execute(new LandlordPlayer(cyclicBarrier));
        executor.execute(new FarmerPlayer2(cyclicBarrier));
        executor.execute(new FarmerPlayer1(cyclicBarrier));
        cyclicBarrier.await();
        // 模拟游戏读取需要 1 秒
        TimeUnit.SECONDS.sleep(1);
        logger.info("游戏加载成功，耗时[{}]毫秒，开始游戏....", System.currentTimeMillis() - startTime);
    }
}