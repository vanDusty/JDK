package cn.van.jdk.tool.todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: ArrayToListTest
 *
 * @author: Van
 * Date:     2019-12-13 23:06
 * Description: 数组转集合
 * Version： V1.0
 */
public class ArrayToListTest {

    /**
     * 常用转换方式
     */
    public void commonMethod() {
        String[] strings = {"first", "second", "third"};
        List<String> list = Arrays.asList(strings);
        System.out.println(list);
        // 以下两个操作都会抛出 UnsupportedOperationException
        list.add("fourth");
        list.remove("first");
        System.out.println(list);
    }

    public void commonMethodOther() {
        String[] strings = {"first", "second", "third"};
        List<String> list = Arrays.asList(strings);
        System.out.println(list);
        // 只操作原数组，不操作集合
        strings[0]= "fourth";
        System.out.println(list);
    }

    public void method1() {
        String[] strings = {"first", "second", "third"};
        List list = new ArrayList(Arrays.asList(strings));
        System.out.println(list);
        // add()/remove() 均有效
        list.add("fourth");
        list.remove("first");
        System.out.println(list);
        // 只操作原数组，不操作集合，集合无影响
        strings[0]= "fourth";
        System.out.println(list);
    }

    public void method2() {
        String[] strings = {"first", "second", "third"};
        List<String> list = new ArrayList<>(strings.length);
        Collections.addAll(list, strings);
        System.out.println(list);
        // add()/remove() 均有效
        list.add("fourth");
        list.remove("first");
        System.out.println(list);
        // 只操作原数组，不操作集合，集合无影响
        strings[0]= "fourth";
        System.out.println(list);
    }
}
