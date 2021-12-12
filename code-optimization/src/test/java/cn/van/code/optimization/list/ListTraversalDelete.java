package cn.van.code.optimization.list;

import cn.van.code.optimization.domain.StudentDomain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (C), 2015-2021, 风尘博客
 * 公众号 : 风尘博客
 * Author:   Van
 * Date:     2021/10/12 2:24 下午
 * Description:
 * Version： V1.0
 */
public class ListTraversalDelete {

    private List<StudentDomain> studentDomains = genData();

    /**
     * for 循环直接删除
     */
    @Test
    public void shouldCompile() {
        System.out.println("studentDomains.size():" + studentDomains.size());
        int index = 0;
        for (int i = 0; i < studentDomains.size(); i++) {
            index ++;
            if (studentDomains.get(i).getScore() < 95.0) {
                studentDomains.remove(studentDomains.get(i));
            }
        }
        System.out.println(studentDomains);
        System.out.println("执行次数：" + index);
    }

    /**
     * 增强 for 循环删除
     */
    @Test
    public void commonError() {
        for (StudentDomain student : studentDomains) {
            if (student.getScore() < 95.0) {
                studentDomains.remove(student);
            }
        }
        System.out.println(studentDomains);
    }

    /**
     * 优化普通 for 循环：移除元素的同时，变更坐标
     */
    @Test
    public void forModifyIndex() {
        for (int i = 0; i < studentDomains.size(); i++) {
            StudentDomain item = studentDomains.get(i);
            if (item.getScore() < 95.0) {
                studentDomains.remove(i);
                // 关键是这里：移除元素同时变更坐标
                i = i - 1;
            }
        }
        System.out.println(studentDomains);
    }
    /**
     * 优化普通 for 循环：倒序遍历
     */
    @Test
    public void forOptimization() {
        List<StudentDomain> studentDomains = genData();
        for (int i = studentDomains.size() - 1; i >= 0; i--) {
            StudentDomain item = studentDomains.get(i);
            if (item.getScore() < 95.0) {
                studentDomains.remove(i);
            }
        }
        System.out.println(studentDomains);
    }

    /**
     * 使用 Iterator 的 remove()
     */
    @Test
    public void iteratorRemove() {
        Iterator<StudentDomain> iterator = studentDomains.iterator();
        while (iterator.hasNext()) {
            StudentDomain student = iterator.next();
            if (student.getScore() < 95.0) {
                iterator.remove();
            }
        }
        System.out.println(studentDomains);
    }

    /**
     * Stream 流的筛除功能
     */
    @Test
    public void streamFilter() {
        List<StudentDomain> studentDomains = genData();
        studentDomains = studentDomains.stream().filter(student -> student.getScore() >= 95.0).collect(Collectors.toList());
        System.out.println(studentDomains);
    }


    /**
     * Collection.removeIf()
     */
    @Test
    public void removeIf() {
        List<StudentDomain> studentDomains = genData();
        studentDomains.removeIf(student -> student.getScore() < 95.0);
        System.out.println(studentDomains);
    }

    /**
     * 造数据方法
     *
     * @return
     */
    public List<StudentDomain> genData() {
        List<StudentDomain> studentDomainList = new ArrayList<>();
        StudentDomain zs = new StudentDomain(1, "张三", "科学", 91.5, "一班");
        StudentDomain ls = new StudentDomain(1, "李四", "科学", 95.0, "一班");
        StudentDomain zw = new StudentDomain(1, "赵五", "科学", 93.5, "一班");
        StudentDomain wl = new StudentDomain(1, "王六", "科学", 100.0, "一班");
        studentDomainList.add(zs);
        studentDomainList.add(ls);
        studentDomainList.add(zw);
        studentDomainList.add(wl);
        return studentDomainList;
    }


}