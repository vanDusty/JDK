package cn.van.jdk.eight.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: DurationPeriodDemo
 *
 * @author: Van
 * Date:     2020-02-03 20:51
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
public class DurationPeriodDemo {

    public static void main(String[] args) {

        duration();
        period();

    }

    public static void duration() {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusDays(1);
        // 通过between()方法创建
        Duration duration = Duration.between(from, to);
        // 通过of()方法创建,该方法参数为时间段长度和时间单位。
        // 7天
        Duration duration1 = Duration.of(7, ChronoUnit.DAYS);
        // 60秒
        Duration duration2 = Duration.of(60, ChronoUnit.SECONDS);
    }

    private static void period() {
        // 通过of方法
        Period period = Period.of(2012, 12, 24);
        // 通过between方法
        Period period1 = Period.between(LocalDate.now(), LocalDate.of(2020,12,31));
    }
}