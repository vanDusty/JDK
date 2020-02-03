package cn.van.jdk.eight.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: CompareAndEditDemo
 *
 * @author: Van
 * Date:     2020-02-03 21:04
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
public class CompareAndEditDemo {

    public static void main(String[] args) {
        // compare();
        // plusAndMinus();
        // edit();
        compute();
    }

    public static void compare() {
        LocalDate thisDay = LocalDate.of(2008, 8, 8);
        LocalDate otherDay = LocalDate.of(2018, 8, 8);

        // 晚于
        boolean isAfter = thisDay.isAfter(otherDay);
        System.out.println(isAfter);

        // 早于
        boolean isBefore = thisDay.isBefore(otherDay);
        System.out.println(isBefore);
    }


    public static void plusAndMinus() {
        // 增加
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime nextYearDay = today.plusYears(1);
        System.out.println("下一年的今天是：" + nextYearDay);
        LocalDateTime nextMonthDay = today.plus(1, ChronoUnit.MONTHS);
        System.out.println("下一个月的今天是：" + nextMonthDay);

        //减少
        LocalDateTime lastMonthDay = today.minusMonths(1);
        LocalDateTime lastYearDay = today.minus(1, ChronoUnit.YEARS);
        System.out.println("一个月前是：" + lastMonthDay);
        System.out.println("一年前是：" + lastYearDay);
    }

    /**
     * 修改时间
     */
    public static void edit() {
        LocalDateTime today = LocalDateTime.now();
        // 修改年为2012年
        LocalDateTime thisYearDay = today.withYear(2012);
        System.out.println("修改年后的时间为：" + thisYearDay);
        // 修改为12月
        LocalDateTime thisMonthDay = today.with(ChronoField.MONTH_OF_YEAR, 12);
        System.out.println("修改月后的时间为：" + thisMonthDay);
    }

    public static void compute() {
        // TemporalAdjusters 的静态方法
        LocalDate today = LocalDate.now();
        // 获取今年的第一天
        LocalDate date = today.with(firstDayOfYear());
        System.out.println("今年的第一天是：" + date);

        // Duration 计算
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusMonths(1);
        Duration duration = Duration.between(from, to);

        // 区间统计换算
        // 总天数
        long days = duration.toDays();
        System.out.println("相隔" + days + "天");
        // 小时数
        long hours = duration.toHours();
        System.out.println("相隔" + hours + "小时");
        // 分钟数
        long minutes = duration.toMinutes();
        System.out.println("相隔" + minutes + "分钟");
    }
}