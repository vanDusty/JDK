package cn.van.jdk.tool.todo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: DeepCopyUtil
 *
 * @author: Van
 * Date:     2019-12-14 22:15
 * Description: 对象深度拷贝工具类(todo)
 * Version： V1.0
 */
public class DeepCopyUtil {

    /**
     * 单个对象的深拷贝，srcObj对应的需实现java.io.Serializable接口
     * @param srcObj obj
     * @return new  obj
     */
    public static Object depthClone(Object srcObj) {
        Object cloneObj = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(out);
            oo.writeObject(srcObj);
            ByteArrayInputStream in = new ByteArrayInputStream(
                    out.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(in);
            cloneObj = oi.readObject();
        } catch (Exception ex) {
            return null;
        }
        return cloneObj;
    }

    /**
     * 多个对象的深拷贝，srcObj对应的需实现java.io.Serializable接口
     * @param list obj
     * @return new list obj
     */
    public static <T> List<T> listDepthClone(List<T> list) {
        List<T> newList = new ArrayList<>();
        for (Object item : list) {
            if (item == null) {
                continue;
            }
            Object val = depthClone(item);
            if (val != null) {
                newList.add((T) val);
            }
        }
        return newList;
    }

}
