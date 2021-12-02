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
 * Date:     2021/10/25 9:53 下午
 * Description:
 * Version： V1.0
 */
public class StreamDemo {

    @Test
    public void createStream() {
        // 通过集合生成
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> listStream = integerList.stream();
        // 通过数组生成
        int[] intArr = new int[]{1, 2, 3, 4, 5};
        IntStream arrStream = Arrays.stream(intArr);

        // 通过值生成
        Stream<Integer> valueStream = Stream.of(1, 2, 3, 4, 5);
        // 合并流
        List<String> strList = Arrays.asList("公众号");
        List<String> strAnotherList = Arrays.asList("风尘博客");
        Stream<String> strStream = Stream.concat(strList.stream(), strAnotherList.stream());


    }


    /**
     * 中间操作：筛选
     */
    @Test
    public void IntermediateOperation() {

        // filter
        List<Integer> filterList = Arrays.asList(1, 1, 2, 3, 4, 5);
        Stream<Integer> filterStream = filterList.stream().filter(i -> i > 3);
        filterStream.forEach(System.out::println);

        // limit
        List<Integer> limitList = Arrays.asList(1, 1, 2, 3, 4, 5);
        Stream<Integer> limitStream = limitList.stream().limit(3);
        limitStream.forEach(System.out::println);

        // skip
        List<Integer> skipList = Arrays.asList(1, 1, 2, 3, 4, 5);
        Stream<Integer> skipStream = skipList.stream().skip(2);
        skipStream.forEach(System.out::println);

        // distinct
        List<Integer> distinctList = Arrays.asList(1, 1, 2, 3, 4, 5);
        Stream<Integer> distinctStream = distinctList.stream().distinct();
        distinctStream.forEach(System.out::println);
    }

    /**
     * 中间操作：映射
     */
    @Test
    public void mapping() {
        List<StudentDomain> studentDomainList = assStudent();

        Stream<String> mapStream = studentDomainList.stream()
                .map(item -> item.getName() + "考试得分: " + item.getScore());
        mapStream.forEach(System.out::println);


        List<String> flatMapList = Arrays.asList("风尘_博客", "微信_公众号");
        Stream<String> flatMapStream = flatMapList.stream()
                .map(w -> w.split("_"))
                .flatMap(Arrays::stream);
        flatMapStream.forEach(System.out::println);
    }

    /**
     * 中间操作：排序
     */
    @Test
    public void sorted() {
        List<StudentDomain> sortedList = assStudent();
        // 自然顺序（正序）
        sortedList = sortedList.stream().sorted(comparing(StudentDomain::getScore)).collect(Collectors.toList());
        sortedList.forEach(System.out::println);
        // 倒序
        sortedList = sortedList.stream().sorted(comparing(StudentDomain::getScore).reversed()).collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }

    /**
     * 终止操作：查找与匹配
     */
    @Test
    public void findAndMatch() {
        List<StudentDomain> studentDomainList = assStudent();
        // max
        Optional<Double> max = studentDomainList.stream().map(StudentDomain::getScore).max(Double::compareTo);
        System.out.println("最高分数：" + max.get());
        // count
        Optional<Double> min = studentDomainList.stream().map(StudentDomain::getScore).min(Double::compareTo);
        System.out.println("最低分数：" + min.get());
        // count
        Long count = studentDomainList.stream().count();
        System.out.println(count);

        List<StudentDomain> foundEmpty = new ArrayList<>();
        List<StudentDomain> foundList = assStudent();
        // 默认
        StudentDomain student = assDefault();
        // findFirst，返回第一个元素
        System.out.println(foundList.stream().findFirst().get().toString());
        // findAny，任意返回一个元素, 若不存在则返回默认
        System.out.println(foundEmpty.stream().findAny().orElse(student));

        // allMatch
        List<Integer> matchList = Arrays.asList(1, 1, 2, 3, 4, 5);
        if (matchList.stream().allMatch(i -> i > 3)) {
            System.out.println("值都大于3");
        }
        // anyMatch
        if (matchList.stream().anyMatch(i -> i > 3)) {
            System.out.println("存在大于3的值");
        }
        // noneMatch
        if (matchList.stream().noneMatch(i -> i > 3)) {
            System.out.println("值都小于3");
        }

    }

    /**
     * 终止操作：collect
     */
    @Test
    public void collect() {
        // toList 取出学生姓名组成新的集合
        List<StudentDomain> studentDomainList = assStudent();
        List<String> toList = studentDomainList.stream()
                .map(studentDomain -> studentDomain.getName())
                .collect(Collectors.toList());
        System.out.println(toList);

        // toMap 取出学生名字和分数，组成map
        Map<String, String> toMap = studentDomainList.stream()
                .collect(Collectors.toMap(
                        // 取学生姓名为key
                        item -> item.getName(),
                        // 取分数为value
                        item -> String.valueOf(item.getScore())));
        System.out.println(toMap);

        // groupingBy 将学生按班级分组
        Map<String, List<StudentDomain>> collect = studentDomainList.stream()
                .collect(Collectors.groupingBy(per -> per.getClassNum()));
        System.out.println(collect);

        // join 取出学生姓名拼成字符串
        String str = studentDomainList.stream()
                .map(student -> student.getName())
                .collect(Collectors.joining(",", "{", "}"));
        System.out.println(str);
    }

    private List<StudentDomain> assStudent() {
        List<StudentDomain> result = new ArrayList<>();
        StudentDomain studentDomain;
        for (int i = 1; i <= 5; i++) {
            studentDomain = new StudentDomain();
            studentDomain.setId(i);
            studentDomain.setName("小" + i);
            studentDomain.setSubject("科学");
            studentDomain.setScore(Double.valueOf(99 - i));
            studentDomain.setClassNum(i % 2 == 0 ? "一班" : "二班");
            result.add(studentDomain);
        }
        return result;
    }

    private StudentDomain assDefault() {
        StudentDomain student = new StudentDomain();
        student.setName("默认");
        student.setId(0);
        student.setScore(100.0);
        student.setSubject("数学");
        return student;
    }
}