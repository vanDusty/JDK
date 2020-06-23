package cn.van.jdk.generic;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: GenericClassTest
 *
 * @author: Van
 * Date:     2019-12-22 23:17
 * Description: 泛型类
 * 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
 * 在实例化泛型类时，必须指定T的具体类型
 * Version： V1.0
 */
public class GenericClassTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 不指定泛型类型
     */
    @Test
    public void genericDemoWithOutType() {
        GenericClass generic = new GenericClass("111111");
        logger.info("generic key is:{}", generic.getKey());
        GenericClass generic1 = new GenericClass(4444);
        logger.info("generic1 key is:{}", generic1.getKey());
        GenericClass generic2 = new GenericClass(55.55);
        logger.info("generic2 key is:{}", generic2.getKey());
        GenericClass generic3 = new GenericClass(false);
        logger.info("generic3 key is:{}", generic3.getKey());
    }

    /**
     * 指定泛型类型
     */
    @Test
    public void genericDemoWithType() {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型，比如这里Integer改为int编译将不通过
        GenericClass<Integer> integerGeneric = new GenericClass<Integer>(123456);
        logger.info("integerGeneric key is:{}", integerGeneric.getKey());

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        GenericClass<String> stringGeneric = new GenericClass<String>("风尘博客");
        logger.info("stringGeneric key is:{}", stringGeneric.getKey());
    }

}
