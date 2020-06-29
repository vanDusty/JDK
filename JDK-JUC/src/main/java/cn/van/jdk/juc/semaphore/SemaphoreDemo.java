package cn.van.jdk.juc.semaphore;

import java.util.Random;
import java.util.concurrent.*;
/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: SemaphoreDemo
 *
 * @author: Van
 * Date:     2020-02-19 20:09
 * Description: 信号量
 * Version： V1.0
 */
public class SemaphoreDemo {
    /**
     * 假设有十桌客人准备就餐
     */
    private final static int tableCount = 10;

    public static void main(String[] args) throws InterruptedException {
        // 信号量，即餐厅只有3桌，仅能提供3桌客人同时就餐
        Semaphore semaphore = new Semaphore(3, true);
        Long startTime = System.currentTimeMillis();
        // 计数器，计算什么时候全部就餐结束
        CountDownLatch countDownLatch = new CountDownLatch(tableCount);
        for (int i = 1; i <= tableCount; i++) {
            final int count = i;
            Thread thread = new Thread(() -> {
                try {
                    // 获取资源，开始处理
                    semaphore.acquire();
                    System.out.println("第" + count + "批桌客人开始就餐");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println("第" + count + "批桌客人就餐结束");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 处理结束，资源释放
                    semaphore.release();
                    countDownLatch.countDown();
                }
            }, "线程" + i);
            thread.start();
        }
        // 等待全部客人就餐结束
        countDownLatch.await();
        System.out.println("所有客人就餐完毕，总耗时" + (System.currentTimeMillis() - startTime) + "毫秒");
    }
}
