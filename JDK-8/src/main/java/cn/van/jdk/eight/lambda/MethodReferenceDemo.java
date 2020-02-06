package cn.van.jdk.eight.lambda;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: MethodReferenceDemo
 *
 * @author: Van
 * Date:     2020-02-06 14:03
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
public class MethodReferenceDemo {

    public static void main(String[] args) {
        instanceMethod();
        staticMethod();
        instanceMethodObject();
    }
    /**
     * 实例对象名::实例方法名
     */
    private static void instanceMethod() {
        UserDomain user = new UserDomain(1L, "Van");

        Supplier<String> sup = () -> user.getUserName();
        System.out.println(sup.get());

        System.out.println("===等价于===");
        Supplier<String> supplier = user::getUserName;
        System.out.println(supplier.get());
    }

    /**
     * 类名::静态方法名
     */
    private static void staticMethod() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println(com.compare(3,9));

        System.out.println("===等价于===");
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(3,9));
    }

    /**
     * 类名::实例方法名
     */
    private static void instanceMethodObject() {
        UserDomain user = new UserDomain(1L, "Van");

        Function<UserDomain, String> fun = (e) -> e.getUserName();
        System.out.println(fun.apply(user));

        System.out.println("===等价于===");
        Function<UserDomain, String> fun2 = UserDomain::getUserName;
        System.out.println(fun2.apply(user));
    }
}
