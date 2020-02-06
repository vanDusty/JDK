package cn.van.jdk.eight.lambda;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: LambdaDemo
 *
 * @author: Van
 * Date:     2020-02-05 19:24
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
public class LambdaDemo {

    public static void main(String[] args) {
        general();
        lambda();
    }

    /**
     * 传统的写法
     */
    public static void general() {
        // 用匿名内部类的方式来创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("公众号：风尘博客！");
            }
        }).run();
    }

    /**
     * Lambda 写法
     */
    public static void lambda() {
        // 使用Lambda来创建线程
        new Thread(() -> System.out.println("公众号：风尘博客！")).run();
    }

}
