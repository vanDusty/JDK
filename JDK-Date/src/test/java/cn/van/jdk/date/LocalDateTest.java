package cn.van.jdk.date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

public class LocalDateTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void localDate() {
        //获取当前年月日
        LocalDate today = LocalDate.now();
        logger.info("当前年月日：[{}]", today);

        // 获取年的两种方式
        int thisYear = today.getYear();
        int thisYearAnother = today.get(ChronoField.YEAR);
        logger.info("今年是：[{}]年", thisYear);
        logger.info("今年是：[{}]年", thisYearAnother);

        // 获取月
        Month thisMonth = today.getMonth();
        logger.info("这个月是：[{}]月", thisMonth.toString());
        // 这是今年的第几个月(两种写法)
        int monthOfYear = today.getMonthValue();
        // int monthOfYear = today.get(ChronoField.MONTH_OF_YEAR);
        logger.info("这个月是今年的第[{}]个月", monthOfYear);
        // 月份的天数
        int length = today.lengthOfMonth();
        logger.info("这个月有[{}]天", length);

        // 获取日的两种方式
        int thisDay = today.getDayOfMonth();
        int thisDayAnother = today.get(ChronoField.DAY_OF_MONTH);
        logger.info("今天是这个月的第[{}]天", thisDay);
        logger.info("今天是这个月的第[{}]天", thisDayAnother);

        // 获取星期
        DayOfWeek thisDayOfWeek = today.getDayOfWeek();
        logger.info("今天是[{}]", thisDayOfWeek.toString());
        // 今天是这周的第几天
        int dayOfWeek = today.get(ChronoField.DAY_OF_WEEK);
        logger.info("今天是这周的第[{}]天", dayOfWeek);

        // 是否为闰年
        boolean leapYear = today.isLeapYear();
        logger.info("今年是闰年：[{}]", leapYear);

        //构造指定的年月日
        LocalDate anotherDay = LocalDate.of(2008, 8, 8);
        logger.info("指定年月日：[{}]", anotherDay);
    }

    @Test
    public void localTime() {
        // 获取当前时间
        LocalTime nowTime = LocalTime.now();
        logger.info("当前时间：[{}]", nowTime);

        //获取小时的两种方式
        int hour = nowTime.getHour();
        int thisHour = nowTime.get(ChronoField.HOUR_OF_DAY);
        logger.info("当前时：[{}]", hour);
        logger.info("当前时：[{}]", thisHour);

        //获取分的两种方式
        int minute = nowTime.getMinute();
        int thisMinute = nowTime.get(ChronoField.MINUTE_OF_HOUR);
        logger.info("当前分：[{}]", minute);
        logger.info("当前分：[{}]", thisMinute);

        //获取秒的两种方式
        int second = nowTime.getSecond();
        int thisSecond = nowTime.get(ChronoField.SECOND_OF_MINUTE);
        logger.info("当前秒：[{}]", second);
        logger.info("当前秒：[{}]", thisSecond);

        // 构造指定时间(最多可到纳秒)
        LocalTime anotherTime = LocalTime.of(20, 8, 8);
        logger.info("构造指定时间：[{}]", anotherTime);
    }

    @Test
    public void localDateTime() {
        // 当前日期和时间
        LocalDateTime today = LocalDateTime.now();
        logger.info("现在是：[{}]", today);

        // 创建指定日期和时间
        LocalDateTime anotherDay = LocalDateTime.of(2008, Month.AUGUST, 8, 8, 8, 8);
        logger.info("创建的指定时间是：[{}]", anotherDay);

        // 拼接日期和时间
        // 使用当前日期，指定时间生成的 LocalDateTime
        LocalDateTime thisTime = LocalTime.now().atDate(LocalDate.of(2008, 8, 8));
        logger.info("拼接的日期是：[{}]", thisTime);
        // 使用当前日期，指定时间生成的 LocalDateTime
        LocalDateTime thisDay = LocalDate.now().atTime(LocalTime.of(12, 24, 12));
        logger.info("拼接的日期是：[{}]", thisDay);
        // 指定日期和时间生成 LocalDateTime
        LocalDateTime thisDayAndTime = LocalDateTime.of(LocalDate.of(2008, 8, 8), LocalTime.of(12, 24, 12));
        logger.info("拼接的日期是：[{}]", thisDayAndTime);

        // 获取LocalDate
        LocalDate todayDate = today.toLocalDate();
        logger.info("今天日期是：[{}]", todayDate);

        // 获取LocalTime
        LocalTime todayTime = today.toLocalTime();
        logger.info("现在时间是：[{}]", todayTime);
    }

    @Test
    public void instantDemo() {
        // 创建Instant对象
        Instant instant = Instant.now();
        // 通过ofEpochSecond方法创建(第一个参数表示秒，第二个参数表示纳秒)
        Instant another = Instant.ofEpochSecond(365 * 24 * 60, 100);

        // 获取到秒数
        long currentSecond = instant.getEpochSecond();
        logger.info("获取到秒数：[{}]", currentSecond);

        // 获取到毫秒数
        long currentMilli = instant.toEpochMilli();
        logger.info("获取到毫秒数：[{}]", currentMilli);
    }


    @Test
    public void durationDemo() {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusDays(1);
        // 通过between()方法创建
        Duration duration = Duration.between(from, to);
        logger.info("result:[{}]", duration);
        // 通过of()方法创建,该方法参数为时间段长度和时间单位。
        // 7天
        Duration duration1 = Duration.of(7, ChronoUnit.DAYS);
        logger.info("result:[{}]", duration1);

        // 60秒
        Duration duration2 = Duration.of(60, ChronoUnit.SECONDS);
        logger.info("result:[{}]", duration2);
    }

    @Test
    public void periodDemo() {
        // 通过of方法
        Period period = Period.of(2012, 12, 24);
        logger.info("result:[{}]", period);

        // 通过between方法
        Period period1 = Period.between(LocalDate.now(), LocalDate.of(2020, 12, 31));
        logger.info("result:[{}]", period1);
    }

    /**
     * 时间比较
     */
    @Test
    public void compare() {
        LocalDate thisDay = LocalDate.of(2008, 8, 8);
        LocalDate otherDay = LocalDate.of(2018, 8, 8);
        // 晚于
        boolean isAfter = thisDay.isAfter(otherDay);
        logger.info("result:{}", isAfter);
        // 早于
        boolean isBefore = thisDay.isBefore(otherDay);
        logger.info("result:{}", isBefore);
    }


    /**
     * 增加/减少年数、月数、天数
     */
    @Test
    public void plusAndMinus() {
        // 增加
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime nextYearDay = today.plusYears(1);
        logger.info("下一年的今天是：[{}]", nextYearDay);
        LocalDateTime nextMonthDay = today.plus(1, ChronoUnit.MONTHS);
        logger.info("下一个月的今天是：[{}]", nextMonthDay);

        //减少
        LocalDateTime lastMonthDay = today.minusMonths(1);
        LocalDateTime lastYearDay = today.minus(1, ChronoUnit.YEARS);
        logger.info("一个月前是：[{}]", lastMonthDay);
        logger.info("一年前是：[{}]", lastYearDay);
    }


    /**
     * 修改时间
     */
    @Test
    public void edit() {
        LocalDateTime today = LocalDateTime.now();
        // 修改年为2012年
        LocalDateTime thisYearDay = today.withYear(2012);
        logger.info("修改年后的时间为：[{}]", thisYearDay);
        // 修改为12月
        LocalDateTime thisMonthDay = today.with(ChronoField.MONTH_OF_YEAR, 12);
        logger.info("修改月后的时间为：[{}]", thisMonthDay);
    }

    /**
     * 时间计算
     */
    @Test
    public void compute() {
        // TemporalAdjusters 的静态方法
        LocalDate today = LocalDate.now();
        // 获取今年的第一天
        LocalDate date = today.with(firstDayOfYear());
        logger.info("今年的第一天是：[{}]", date);

        // Duration 计算
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusMonths(1);
        Duration duration = Duration.between(from, to);

        // 区间统计换算
        // 总天数
        long days = duration.toDays();
        logger.info("相隔:[{}]天", days);
        // 小时数
        long hours = duration.toHours();
        logger.info("相隔:[{}]小时", hours);
        // 分钟数
        long minutes = duration.toMinutes();
        logger.info("相隔:[{}]分钟", minutes);
    }


    /**
     * 格式化
     */
    @Test
    public void format() {
        LocalDate today = LocalDate.now();
        // 两种默认格式化时间方式
        String todayStr1 = today.format(DateTimeFormatter.BASIC_ISO_DATE);
        String todayStr2 = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        logger.info("格式化时间：[{}]", todayStr1);
        logger.info("格式化时间：[{}]", todayStr2);
        //自定义格式化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayStr3 = today.format(dateTimeFormatter);
        logger.info("自定义格式化时间：[{}]", todayStr3);
    }

    /**
     * 解析时间
     */
    @Test
    public void parse() {
        LocalDate date1 = LocalDate.parse("20080808", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2008-08-08", DateTimeFormatter.ISO_LOCAL_DATE);
        logger.info("result:[{}]", date1);
        logger.info("result:[{}]", date2);
    }

    /**
     * 周期性事件
     */
    @Test
    public void periodicEvent() {

        // MonthDay 每年都会发生的事件(忽略年份),例如生日。
        LocalDate createTime = LocalDate.of(2018, 2, 14);
        MonthDay birthday = MonthDay.of(createTime.getMonthValue(), createTime.getDayOfMonth());

        MonthDay todayMonthDay = MonthDay.from(LocalDate.now()); // 获取今天的月日
        if (todayMonthDay.equals(birthday)) {
            logger.info("Many Many happy today!!");
        } else {
            logger.info("Sorry, today is not your birthday");
        }

        // YearMonth,表示信用卡到期日等等周期性时间，这个类还可以返回当月的天数，在判断2月有28天还是29天时非常有用。
        YearMonth currentYearMonth = YearMonth.now();
        logger.info("Days in month year [{}]:[{}]", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY); // 指定时间
        logger.info("Your credit card expires on [{}]", creditCardExpiry);
    }
}
