package cn.van.jdk.eight.threadlocal;

import cn.van.jdk.eight.threadlocal.service.Service1;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: ThreadLocalTest
 *
 * @author: Van
 * Date:     2020-01-11 11:15
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ThreadLocalTest {

    //创建ThreadLocal变量
    private static ThreadLocal<String> localParam = new ThreadLocal<>();

    /**
     * ThreadLocal 演示
     */
    @Test
    public void threadLocalDemo() {
        //创建2个线程，分别设置不同的值
        new Thread(() -> {
            localParam.set("Hello 风尘博客!");
            //打印当前线程本地内存中的localParam变量的值
            log.info("{}:{}", Thread.currentThread().getName(), localParam.get());
        }, "T1").start();
        new Thread(() -> {
            log.info("{}:{}", Thread.currentThread().getName(), localParam.get());
        }, "T2").start();
    }

    /**
     * ThreadLocal 传参
     */
    @Test
    public void threadForParams() {
        new Service1().process();
    }

    /**
     * 空指针问题
     */
    @Test
    public void threadLocalNPE() {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        //如果get方法返回值为基本类型，则会报空指针异常，如果是包装类型就不会出错
        System.out.println(threadLocalNPE.get());
    }
}
