package cn.van.jdk.eight.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: FunctionalInterfaceDemo
 *
 * @author: Van
 * Date:     2020-02-05 22:43
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
public class FunctionalInterfaceDemo {


    public static void main(String[] args) {
        // 修改参数
        consumerDemo(3, s -> System.out.println(s * 3));

        // 生成10个以内的随机书
        List<Integer> numList = supplierDemo(10, () -> (int)(100 * Math.random()));
        System.out.println(numList);

        // 处理字符串
        String str1 = functionDemo("Hello!风尘博客", s -> s.substring(6));
        System.out.println(str1);
        String str2 = functionDemo("vanDusty", s -> s.toUpperCase());
        System.out.println(str2);

        // 将满足条件的字符串放入集合
        List<String> list = Arrays.asList("hello", "van", "function", "predicate");
        List<String> newList = predicateDemo(list, s -> s.length() > 5);
        System.out.println(newList);

        // 字符串转大写
        String newStr = selfFunctionalInterface((str) -> str.toUpperCase(), "abc");
        System.out.println(newStr);
    }


    /**
     * Consumer<T> 消费型接口
     *
     * @param value
     * @param consumer
     */
    public static void consumerDemo(Integer value, Consumer<Integer> consumer) {
        consumer.accept(value);
    }

    /**
     * Supplier<T> 供给型接口
     *
     * @param num
     * @param supplier
     * @return
     */
    public static List<Integer> supplierDemo(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }

    /**
     * Function<T, R> 函数型接口
     * @param str
     * @param function
     * @return
     */
    public static String functionDemo(String str, Function<String, String> function) {
        return function.apply(str);
    }


    /**
     * Predicate<T> 断言型接口
     * @param list
     * @param predicate
     * @return
     */
    public static List<String> predicateDemo(List<String> list, Predicate<String> predicate) {
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                newList.add(s);
            }
        }
        return newList;
    }

    /**
     * 自定义函数式接口
     * @param selfFunctionalInterface
     * @param str
     * @return
     */
    public static String selfFunctionalInterface(SelfFunctionalInterface<String> selfFunctionalInterface, String str) {
        return selfFunctionalInterface.getValue(str);
    }

}
