package cn.van.code.optimization.domain;

/**
 * Copyright (C), 2015-2021, 风尘博客
 * 公众号 : 风尘博客
 * @Author:   Van
 * Date:     2021/11/1 10:39 下午
 * Description: 学生
 * Version： V1.0
 */
public class StudentDomain {

    private Integer id;

    private String name;

    private String subject;

    private Double score;

    private String classNum;

    public StudentDomain() {
    }

    public StudentDomain(Integer id, String name, String subject, Double score, String classNum) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.score = score;
        this.classNum = classNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    @Override
    public String toString() {
        return "StudentDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                ", classNum='" + classNum + '\'' +
                '}';
    }
}