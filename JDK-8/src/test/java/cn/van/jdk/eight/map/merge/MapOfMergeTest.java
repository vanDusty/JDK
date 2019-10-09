package cn.van.jdk.eight.map.merge;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2015-2019, 风尘博客
 * 公众号 : 风尘博客
 * FileName: MapOfMergeTest
 *
 * @author: Van
 * Date:     2019-10-08 23:31
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */

// 业务逻辑：有一个学生成绩对象的列表，对象包含学生姓名、科目、科目分数三个属性，要求求得每个学生的总成绩
@Slf4j
public class MapOfMergeTest {

    @Test
    public void testAll() {
        // 一般写法
        normalMethod();
        // merge()方法
        mergeMethod();
    }

    /**
     * 一般写法
     * 思路：用Map的一组key/value存储一个学生的总成绩，学生姓名作为key,总成绩为value
     * 1. Map中不存在指定的key时，将传入的value设置为key的值；
     * 2. 当key存在值时,取出存在的值与当前值相加，然后放入Map中
     */
    public void normalMethod() {
        Long startTime = System.currentTimeMillis();
        // 造一个学生成绩列表
        List<StudentEntity> studentEntityList = buildATestList();

        Map<String, Integer> studentScore = new HashMap<>();
        studentEntityList.forEach(studentEntity -> {
            if (studentScore.containsKey(studentEntity.getStudentName())) {
                studentScore.put(studentEntity.getStudentName(),
                        studentScore.get(studentEntity.getStudentName()) + studentEntity.getScore());
            } else {
                studentScore.put(studentEntity.getStudentName(), studentEntity.getScore());
            }
        });
        log.info("各个学生成绩:{},耗时：{}ms",studentScore, System.currentTimeMillis() - startTime);
    }

    /**
     * Map.merge()写法
     */
    public void mergeMethod() {
        Long startTime = System.currentTimeMillis();
        // 造一个学生成绩列表
        List<StudentEntity> studentEntityList = buildATestList();
        Map<String, Integer> studentScore = new HashMap<>();
        studentEntityList.forEach(studentEntity -> studentScore.merge(
                studentEntity.getStudentName(),
                studentEntity.getScore(),
                Integer::sum));
        log.info("各个学生成绩:{},耗时：{}ms",studentScore, System.currentTimeMillis() - startTime);
    }

    /**
     * 造一个学生成绩列表的数据
     * @return
     */
    private List<StudentEntity> buildATestList() {
        List<StudentEntity> studentEntityList = new ArrayList<>();
        StudentEntity studentEntity1 = new StudentEntity() {{
            setStudentName("张三");
            setSubject("语文");
            setScore(60);
        }};
        StudentEntity studentEntity2 = new StudentEntity() {{
            setStudentName("张三");
            setSubject("数学");
            setScore(70);
        }};
        StudentEntity studentEntity3 = new StudentEntity() {{
            setStudentName("张三");
            setSubject("英语");
            setScore(80);
        }};
        StudentEntity studentEntity4 = new StudentEntity() {{
            setStudentName("李四");
            setSubject("语文");
            setScore(85);
        }};
        StudentEntity studentEntity5 = new StudentEntity() {{
            setStudentName("李四");
            setSubject("数学");
            setScore(75);
        }};
        StudentEntity studentEntity6 = new StudentEntity() {{
            setStudentName("李四");
            setSubject("英语");
            setScore(65);
        }};
        StudentEntity studentEntity7 = new StudentEntity() {{
            setStudentName("王五");
            setSubject("语文");
            setScore(80);
        }};
        StudentEntity studentEntity8 = new StudentEntity() {{
            setStudentName("王五");
            setSubject("数学");
            setScore(85);
        }};
        StudentEntity studentEntity9 = new StudentEntity() {{
            setStudentName("王五");
            setSubject("英语");
            setScore(90);
        }};

        studentEntityList.add(studentEntity1);
        studentEntityList.add(studentEntity2);
        studentEntityList.add(studentEntity3);
        studentEntityList.add(studentEntity4);
        studentEntityList.add(studentEntity5);
        studentEntityList.add(studentEntity6);
        studentEntityList.add(studentEntity7);
        studentEntityList.add(studentEntity8);
        studentEntityList.add(studentEntity9);

        return studentEntityList;
    }

}
