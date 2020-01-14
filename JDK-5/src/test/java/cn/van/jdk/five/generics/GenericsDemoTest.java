package cn.van.jdk.five.generics;

import cn.van.jdk.five.generic.Generic;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: GenericsDemoTest
 *
 * @author: Van
 * Date:     2019-12-13 23:06
 * Description: 泛型
 * Version： V1.0
 */
// @RunWith(SpringRunner.class)
// @SpringBootTest
@Slf4j
public class GenericsDemoTest {

    /**
     * 未指定泛型演示
     */
    @Test
    public void genericDemo() {
        List list = new ArrayList();
        list.add("风尘博客");
        list.add(100);

        for(int i = 0; i< list.size();i++){
            String item = (String)list.get(i);
            log.info("item:{}", item);
        }
    }

    /**
     * 泛型指定类型
     */
    @Test
    public void genericDemoWithType() {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型，比如这里Integer改为int编译将不通过
        Generic<Integer> integerGeneric = new Generic<Integer>(123456);
        log.info("integerGeneric key is:{}", integerGeneric.getKey());

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> stringGeneric = new Generic<String>("风尘博客");
        log.info("stringGeneric key is:{}", stringGeneric.getKey());
    }

    /**
     * 泛型不指定类型
     */
    @Test
    public void genericDemoWithOutType() {
        Generic generic = new Generic("111111");
        Generic generic1 = new Generic(4444);
        Generic generic2 = new Generic(55.55);
        Generic generic3 = new Generic(false);
        log.info("generic key is:{}",generic.getKey());
        log.info("generic1 key is:{}",generic1.getKey());
        log.info("generic2 key is:{}",generic2.getKey());
        log.info("generic3 key is:{}",generic3.getKey());
    }

    @Test
    public void boundaryForUp() {
    }
}
