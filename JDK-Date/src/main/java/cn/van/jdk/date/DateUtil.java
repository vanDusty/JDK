package cn.van.jdk.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: DateUtil
 *
 * @author: Van
 * Date:     2019-12-22 23:17
 * Description: 1.5 日期时间处理工具类
 * Version： V1.0
 */
public class DateUtil {
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String YYYYMMDDHHMMSS_CHINESE = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String YYYYMMDD_CHINESE = "yyyy年MM月dd日";
    public static final String MMDD_CHINESE = "MM月dd日";

    public static final long MILLISECONDS_FOR_ONE_MINUTE = 60 * 1000;
    public static final long MILLISECONDS_FOR_ONE_HOUR = 60 * MILLISECONDS_FOR_ONE_MINUTE;
    public static final long MILLISECONDS_FOR_ONE_DAY = 24 * MILLISECONDS_FOR_ONE_HOUR;

    /**
     * 计算两个时间的间隔天数
     */
    public static int calcIntervalDays(Date date1, Date date2) {
        if (date2.after(date1)) {
            return Long.valueOf((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24)).intValue();
        } else if (date2.before(date1)) {
            return Long.valueOf((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24)).intValue();
        } else {
            return 0;
        }
    }
    /**
     * 判断两个时间是否是同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return calcIntervalDays(date1, date2) == 0;
    }

    /**
     * 计算两个时间的间隔小时，只会整除
     */
    public static int calcIntervalOurs(Date date1, Date date2) {
        if (date2.after(date1)) {
            return Long.valueOf((date2.getTime() - date1.getTime()) / (1000 * 60 * 60)).intValue();
        } else if (date2.before(date1)) {
            return Long.valueOf((date1.getTime() - date2.getTime()) / (1000 * 60 * 60)).intValue();
        } else {
            return 0;
        }
    }

    /**
     * 计算两个时间的间隔分钟，只会整除
     */
    public static int calcIntervalMinutes(Date date1, Date date2) {
        if (date2.after(date1)) {
            return Long.valueOf((date2.getTime() - date1.getTime()) / (1000 * 60)).intValue();
        } else if (date2.before(date1)) {
            return Long.valueOf((date1.getTime() - date2.getTime()) / (1000 * 60)).intValue();
        } else {
            return 0;
        }
    }

    /**
     * 返回日期对应的是星期几
     */
    public static int dayOfWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int dayOfWeek;
        if (ca.get(Calendar.DAY_OF_WEEK) == 1) {
            dayOfWeek = 7;
        } else {
            dayOfWeek = ca.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayOfWeek;
    }

    /**
     * 获取今天当前的总分钟数，如今天16:54，则返回1014
     */
    public static int getTodayMinutes() {
        Calendar ca = Calendar.getInstance();
        int hours = ca.get(Calendar.HOUR_OF_DAY);
        int minutes = ca.get(Calendar.MINUTE);
        return hours * 60 + minutes;
    }

    /**
     * 将String转成Date，默认时区东八区，TimeZone.getTimeZone("Asia/Shanghai")
     *
     * @param dateStr 含格式的时间字符串（分隔符可以是"/"、"-"、中文）
     * @return Date
     */
    public static Date stringParseDate(String dateStr) {
        SimpleDateFormat format = null;
        if (dateStr.contains("/")) {
            if (dateStr.contains(":") && dateStr.contains(" ")) {
                format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            } else {
                format = new SimpleDateFormat("yyyy/MM/dd");
            }
        } else if (dateStr.contains("-")) {
            if (dateStr.contains(":") && dateStr.contains(" ")) {
                format = new SimpleDateFormat(YYYYMMDDHHMMSS);
            } else {
                format = new SimpleDateFormat(YYYYMMDD);
            }
        } else if (dateStr.contains("年") && dateStr.contains("月") && dateStr.contains("日") && dateStr.contains("时") && dateStr.contains("分") && dateStr.contains("秒")) {
            format = new SimpleDateFormat(YYYYMMDDHHMMSS_CHINESE);
        } else if (dateStr.contains("年") && dateStr.contains("月") && dateStr.contains("日")) {
            format = new SimpleDateFormat(YYYYMMDD_CHINESE);
        } else if (!dateStr.contains("年") && dateStr.contains("月") && dateStr.contains("日")) {
            format = new SimpleDateFormat(MMDD_CHINESE);
        }
        if (format == null) {
            return null;
        }
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Date转成String，默认时区东八区，TimeZone.getTimeZone("Asia/Shanghai")
     *
     * @param date
     * @param format
     * @return String
     */
    public static String dateToString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取昨天
     * @return
     */
    public static Date getYesterday() {
        return offsiteDate(new Date(), Calendar.DAY_OF_YEAR, -1);
    }

    /**
     * 获取上周今天
     * @return
     */
    public static Date getLastWeek() {
        return offsiteDate(new Date(), Calendar.WEEK_OF_YEAR, -1);
    }

    /**
     * 获取上个月今天
     * @return
     */
    public static Date getLastMouth() {
        return offsiteDate(new Date(), Calendar.MONTH, -1);
    }

    /**
     * 获取指定日期偏移指定时间后的时间
     * @param date 基准日期
     * @param calendarField 偏移的粒度大小（小时、天、月等）使用Calendar中的常数
     * @param offsite 偏移量，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     */
    public static Date offsiteDate(Date date, int calendarField, int offsite){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendarField, offsite);
        return cal.getTime();
    }

    /**
     * 获取某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        //把日期设置为当月第一天
        a.set(Calendar.DATE, 1);
        //日期回滚一天，也就是最后一天
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 判断是否是闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeap(int year) {
        return ((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0);
    }

    /**
     * 由出生日期获得年龄
     * @param birthDay
     * @return
     */
    public static int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth){
                    age--;
                }
            }else{
                age--;
            }
        }
        return age;
    }
}
