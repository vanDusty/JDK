package cn.van.jdk.five.generic.interfaceimpl;

import java.util.Random;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: VegetablesGenerator
 *
 * @author: Van
 * Date:     2019-12-22 23:37
 * Description: 传入泛型实参
 * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
 * Version： V1.0
 */
public class VegetablesGenerator implements Generator<String>{

    private String[] vegetables = new String[]{"Potato", "Tomato"};

    public String next() {
        Random rand = new Random();
        return vegetables[rand.nextInt(2)];
    }
}
