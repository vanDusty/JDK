package cn.van.jdk.five.generic.boundary;

import cn.van.jdk.five.generic.Generic;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: GenericTest
 *
 * @author: Van
 * Date:     2020-01-01 21:22
 * Description: 泛型上下边界
 * Version： V1.0
 */
@Slf4j
public class GenericTest {

    /**
     * 上界通配符
     * @param obj
     */
    public void showKeyValue(Generic<? extends Number> obj){
        log.info("value is {}", obj.getKey());
    }

    @Test
    public void testForUp() {
        Generic<String> generic1 = new Generic<String>("11111");
        Generic<Integer> generic2 = new Generic<Integer>(2222);
        Generic<Float> generic3 = new Generic<Float>(2.4f);
        Generic<Double> generic4 = new Generic<Double>(2.56);

        /*// 这一行代码编译器会提示错误，因为String类型并不是Number类型的子类
        showKeyValue(generic1);*/

        showKeyValue(generic2);
        showKeyValue(generic3);
        showKeyValue(generic4);
    }
}
