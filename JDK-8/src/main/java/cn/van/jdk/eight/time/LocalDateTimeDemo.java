package cn.van.jdk.eight.time;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: LocalDateTimeDemo
 *
 * @author: Van
 * Date:     2020-02-03 20:31
 * Description: LocalDateTime 案例
 * Version： V1.0
 */
public class LocalDateTimeDemo {

    public static void main(String[] args) {
        localDate();
        localTime();
        localDateTime();
    }

    public static void localDate() {
        //获取当前年月日
        LocalDate today = LocalDate.now();
        System.out.println("当前年月日：" + today);

        // 获取年的两种方式
        int thisYear = today.getYear();
        int thisYearAnother = today.get(ChronoField.YEAR);
        System.out.println("今年是" + thisYear + "年");
        System.out.println("今年是" + thisYearAnother + "年");

        // 获取月
        Month thisMonth = today.getMonth();
        System.out.println(thisMonth.toString());
        // 这是今年的第几个月(两种写法)
        int monthOfYear = today.getMonthValue();
        // int monthOfYear = today.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("这个月是今年的第" + monthOfYear + "个月");
        // 月份的天数
        int length = today.lengthOfMonth();
        System.out.println("这个月有" + length + "天");

        // 获取日的两种方式
        int thisDay = today.getDayOfMonth();
        int thisDayAnother = today.get(ChronoField.DAY_OF_MONTH);
        System.out.println("今天是这个月的第" + thisDay + "天");
        System.out.println("今天是这个月的第" + thisDayAnother + "天");

        // 获取星期
        DayOfWeek thisDayOfWeek = today.getDayOfWeek();
        System.out.println(thisDayOfWeek.toString());
        // 今天是这周的第几天
        int dayOfWeek = today.get(ChronoField.DAY_OF_WEEK);
        System.out.println("今天是这周的第" + dayOfWeek + "天");

        // 是否为闰年
        boolean leapYear = today.isLeapYear();
        System.out.println("今年是闰年：" + leapYear);

        //构造指定的年月日
        LocalDate anotherDay = LocalDate.of(2008, 8, 8);
        System.out.println("指定年月日：" + anotherDay);
    }

    public static void localTime() {
        // 获取当前时间
        LocalTime nowTime = LocalTime.now();
        System.out.println("当前时间：" + nowTime);

        //获取小时的两种方式
        int hour = nowTime.getHour();
        int thisHour = nowTime.get(ChronoField.HOUR_OF_DAY);
        System.out.println("当前时：" + hour);
        System.out.println("当前时：" + thisHour);


        //获取分的两种方式
        int minute = nowTime.getMinute();
        int thisMinute = nowTime.get(ChronoField.MINUTE_OF_HOUR);
        System.out.println("当前分：" + minute);
        System.out.println("当前分：" + thisMinute);

        //获取秒的两种方式
        int second = nowTime.getSecond();
        int thisSecond = nowTime.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println("当前秒：" + second);
        System.out.println("当前秒：" + thisSecond);

        // 构造指定时间(最多可到纳秒)
        LocalTime anotherTime = LocalTime.of(20, 8, 8);
        System.out.println("构造指定时间：" + anotherTime);
    }

    public static void localDateTime() {
        // 当前日期和时间
        LocalDateTime today = LocalDateTime.now();
        System.out.println("现在是：" + today);

        // 创建指定日期和时间
        LocalDateTime anotherDay = LocalDateTime.of(2008, Month.AUGUST, 8, 8, 8, 8);
        System.out.println("创建的指定时间是：" + anotherDay);

        // 拼接日期和时间
        // 使用当前日期，指定时间生成的 LocalDateTime
        LocalDateTime thisTime = LocalTime.now().atDate(LocalDate.of(2008, 8, 8));
        System.out.println("拼接的日期是：" + thisTime);
        // 使用当前日期，指定时间生成的 LocalDateTime
        LocalDateTime thisDay = LocalDate.now().atTime(LocalTime.of(12, 24, 12));
        System.out.println("拼接的日期是：" + thisDay);
        // 指定日期和时间生成 LocalDateTime
        LocalDateTime thisDayAndTime = LocalDateTime.of(LocalDate.of(2008, 8, 8), LocalTime.of(12, 24, 12));
        System.out.println("拼接的日期是：" + thisDayAndTime);

        // 获取LocalDate
        LocalDate todayDate = today.toLocalDate();
        System.out.println("今天日期是：" + todayDate);

        // 获取LocalTime
        LocalTime todayTime = today.toLocalTime();
        System.out.println("现在时间是：" + todayTime);
    }
}