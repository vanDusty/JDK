package cn.van.jdk.eight.lambda;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: ConstructorReferenceDemo
 *
 * @author: Van
 * Date:     2020-02-06 15:03
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
public class ConstructorReferenceDemo {

    public static void main(String[] args) {
        object();
        array();
    }

    /**
     * 构造器引用
     */
    private static void object() {
        // UserDomain 中必须有一个 UserDomain(String userName) 的构造器,下同
        Function<String,UserDomain> fun = (n) -> new UserDomain(n);
        fun.apply("Van");

        System.out.println("===等价于===");
        Function<String,UserDomain> function = UserDomain::new;
        function.apply("Van");

        // 带两个参数的构造器引用就要用BiFunction，多个参数的话，还可以自定义一个这样的函数式接口
        BiConsumer<Long, String> biConsumer = UserDomain :: new;
        biConsumer.accept(1L,"Van");
    }

    /**
     * 数组引用
     */
    private static void array() {
        //传统Lambda实现
        Function<Integer,int[]> function = (i) -> new int[i];
        int[] apply = function.apply(10);
        System.out.println(apply.length);

        //数组类型引用实现
        function = int[] ::new;
        apply = function.apply(100);
        System.out.println(apply.length);
    }
}
