package cn.van.jdk.eight.threadlocal;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: ThreadLocalNPE
 *
 * @author: Van
 * Date:     2020-01-11 17:25
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    /**
     * 当前返回值为基本类型，会报空指针异常，如果改成包装类型Long就不会出错
     * @return
     */
    public long get() {
        return longThreadLocal.get();
    }

}
