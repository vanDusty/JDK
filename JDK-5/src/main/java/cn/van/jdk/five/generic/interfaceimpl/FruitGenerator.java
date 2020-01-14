package cn.van.jdk.five.generic.interfaceimpl;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: FruitGenerator
 *
 * @author: Van
 * Date:     2019-12-22 23:17
 * Description: 未传入泛型实参
 * 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
 * Version： V1.0
 */
public class FruitGenerator<T> implements Generator<T>{

    public T next() {
        return null;
    }
}
