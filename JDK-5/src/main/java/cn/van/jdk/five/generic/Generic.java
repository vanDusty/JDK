package cn.van.jdk.five.generic;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: Generic
 *
 * @author: Van
 * Date:     2019-12-22 23:17
 * Description: 泛型类
 * 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
 * 在实例化泛型类时，必须指定T的具体类型
 * Version： V1.0
 */
public class Generic<T> {
    /**
     * key这个成员变量的类型为T,T的类型由外部指定
     */
    private T key;

    /**
     * 泛型构造方法形参key的类型也为T，T的类型由外部指定
     * @param key
     */
    public Generic(T key) {
        this.key = key;
    }

    /**
     * 泛型方法getKey()的返回值类型为T，T的类型由外部指定
     * @return
     */
    public T getKey(){
        return key;
    }
}
