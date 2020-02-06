package cn.van.jdk.eight.lambda;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: LambdaGrammar
 *
 * @author: Van
 * Date:     2020-02-05 21:32
 * Description: Lambda 语法
 * Version： V1.0
 */
public class LambdaGrammar {

    public static void main(String[] args) {
        noParam();
        oneParam();
        params();
        one();
    }


    /**
     * 无参，无返回值，Lambda 体只需一条语句。
     */
    public static void noParam() {
        Runnable r1 = () -> System.out.println("noParam Test!");
        r1.run();
    }

    /**
     * Lambda 需要一个参数
     */
    public static void oneParam() {
        // Consumer<String> con = (s) -> System.out.println(s);
        // 参数的小括号可以省略。
        Consumer<String> con = s -> System.out.println(s);
        con.accept("oneParam Test!");
    }

    /**
     * Lambda 需要多个参数，并且有返回值。
     */
    public static void params() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("params Test!");
            // 比较x/y的大小
            return Integer.compare(x, y);
        };
        System.out.println(com.compare(1, 2));
    }

    /**
     * 当 Lambda 体只有一条语句时，return 与大括号可以省略。
     */
    public static void one() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println(com.compare(1, 2));
    }

    /**
     * 类型推断
     */
    public static void typeInference() {
        //Integer 类型可以省略
        Comparator<Integer> com = (Integer x,Integer y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
        // 类型推断
        BinaryOperator<Long> addImplicit = (x, y) -> x + y;
    }
}
