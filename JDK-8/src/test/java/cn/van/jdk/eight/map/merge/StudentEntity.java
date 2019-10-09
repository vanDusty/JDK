package cn.van.jdk.eight.map.merge;

import lombok.Data;

/**
 * Copyright (C), 2015-2019, 风尘博客
 * 公众号 : 风尘博客
 * FileName: StudentEntity
 *
 * @author: Van
 * Date:     2019-10-08 23:27
 * Description: 学生对象
 * Version： V1.0
 */
@Data
public class StudentEntity {
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 学科
     */
    private String subject;
    /**
     * 分数
     */
    private Integer score;
}
