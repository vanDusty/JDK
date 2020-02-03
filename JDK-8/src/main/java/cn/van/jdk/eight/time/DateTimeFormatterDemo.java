package cn.van.jdk.eight.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: DateTimeFormatterDemo
 *
 * @author: Van
 * Date:     2020-02-03 21:47
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
public class DateTimeFormatterDemo {

    public static void main(String[] args) {

        format();

        parse();
    }

    /**
     * 格式化
     */
    public static void format() {
        LocalDate today = LocalDate.now();
        // 两种默认格式化时间方式
        String todayStr1 = today.format(DateTimeFormatter.BASIC_ISO_DATE);
        String todayStr2 = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("格式化时间：" + todayStr1);
        System.out.println("格式化时间：" + todayStr2);
        //自定义格式化
        DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayStr3 = today.format(dateTimeFormatter);
        System.out.println("自定义格式化时间：" + todayStr3);
    }

    /**
     * 解析时间
     */
    public static void parse() {
        LocalDate date1 = LocalDate.parse("20080808", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2008-08-08", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date1);
        System.out.println(date2);
    }
}
