package cn.van.jdk.lambda.function;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: SelfFunctionalInterface
 *
 * @author: Van
 * Date:     2020-02-06 13:21
 * Description: @FunctionalInterface 标注该接口会被设计成一个函数式接口，否则会编译错误
 * Version： V1.0
 */
@FunctionalInterface
public interface SelfFunctionalInterface<T> {

    T getValue(T t);
}
