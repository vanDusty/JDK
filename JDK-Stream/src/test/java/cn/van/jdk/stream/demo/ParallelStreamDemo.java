package cn.van.jdk.stream.demo;

import cn.van.jdk.stream.domain.StudentDomain;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;


/**
 * Copyright (C), 2015-2021, 风尘博客
 * 公众号 : 风尘博客
 * Author:   Van
 * Date:     2021/10/28 9:53 下午
 * Description:
 * Version： V1.0
 */
public class ParallelStreamDemo {

    @Test
    public void createParallelStream() {

        List<String> flatMapList = Arrays.asList("风尘_博客", "微信_公众号");
        Stream<String> parallelStream = flatMapList.parallelStream();

        Stream<String> stream = flatMapList.stream();
        Stream<String> parallelStreamAnother = stream.parallel();
    }


    @Test
    public void groupingBy() {
        List<StudentDomain> studentDomainList = assStudent();

        // 将学生按班级分组
        Map<String, List<StudentDomain>> group1 = studentDomainList.stream()
                .collect(Collectors.groupingBy(per -> per.getClassNum()));
        System.out.println(group1);

        // 将学生先按班级分组，再按学科分组
        Map<String, Map<String, List<StudentDomain>>> group2 = studentDomainList.stream().collect(Collectors.groupingBy(StudentDomain::getClassNum, Collectors.groupingBy(StudentDomain::getSubject)));
        System.out.println(group2);
    }

    private List<StudentDomain> assStudent() {
        List<StudentDomain> result = new ArrayList<>();
        StudentDomain studentDomain;
        for (int i = 1; i <= 6; i++) {
            studentDomain = new StudentDomain();
            studentDomain.setId(i);
            studentDomain.setName("小" + i);
            studentDomain.setSubject(i % 3 == 0 ? "科学" : "数学");
            studentDomain.setScore(Double.valueOf(99 - i));
            studentDomain.setClassNum(i % 2 == 0 ? "一班" : "二班");
            result.add(studentDomain);
        }
        return result;
    }


    @Test
    public void toMap(){
        List<StudentDomain> studentDomainList = assStudent();
        // toMap 取出学生名字和分数，组成map
        Map<String, String> toMap = studentDomainList.stream()
                .collect(Collectors.toMap(
                        // 取学生姓名为key
                        item -> item.getName(),
                        // 取分数为value
                        item -> String.valueOf(item.getScore())));
        System.out.println(toMap);

        // 以班级为科目为 key 取出分数就会报错
//        Map<String, String> errorMap = studentDomainList.stream()
//                .collect(Collectors.toMap(
//                        // 取学生姓名为key
//                        item -> item.getSubject(),
//                        // 取分数为value
//                        item -> String.valueOf(item.getScore())));

        // 出现重复时，取前面 value 的值，或者取后面放入的 value 值，覆盖先前的 value 值
        Map<String, String> map1 = studentDomainList.stream()
                .collect(Collectors.toMap(
                        item -> item.getSubject(),
                        item -> String.valueOf(item.getScore()), (v1, v2) -> v1));
        System.out.println(map1);
        // Map的value可以储存一个list，把重复key的值放入list，再存到value中
        Map<String, List<String>> map2 = studentDomainList.stream()
                .collect(Collectors.toMap(
                        StudentDomain::getSubject,
                        e -> Arrays.asList(String.valueOf(e.getScore())),
                        (List<String> oldList, List<String> newList) -> {
                            oldList = Stream.concat(oldList.stream(), newList.stream()).collect(Collectors.toList());
                            return oldList;
                        }));
        System.out.println(map2);
    }

    @Test
    public void toMapError(){
        List<StudentDomain> studentDomainList = assStudent();
        Map<String, List<String>> map2 = studentDomainList.stream()
                .collect(Collectors.toMap(
                        StudentDomain::getSubject,
                        e -> Arrays.asList(String.valueOf(e.getScore())),
                        (List<String> oldList, List<String> newList) -> {
                            oldList.addAll(newList);
                            return oldList;
                        }));
        System.out.println(map2);
    }


}