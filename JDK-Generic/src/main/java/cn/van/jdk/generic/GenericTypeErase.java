package cn.van.jdk.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: GenericTypeErase
 *
 * @author: Van
 * Date:     2020-01-01 23:02
 * Description: Type erase
 * Version： V1.0
 */
public class GenericTypeErase {

    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        System.out.println(l1.getClass() == l2.getClass());
    }
}
