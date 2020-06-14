package cn.van.jdk.date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtilTest {
    protected Logger logger= LoggerFactory.getLogger(getClass());

    @Test
    public void calcIntervalDays() {
        Date date = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        Date toDay = new Date();
        logger.info("calcIntervalDays:{}", DateUtil.calcIntervalDays(toDay, date));
    }

    @Test
    public void isSameDay() {
        Date date = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        Date toDay = new Date();
        logger.info("isSameDay:{}", DateUtil.isSameDay(toDay, date));
    }

    @Test
    public void calcIntervalOurs() {
        Date date1 = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        Date date2 = new GregorianCalendar(2020, Calendar.JANUARY, 2).getTime();
        logger.info("calcIntervalOurs:{}", DateUtil.calcIntervalOurs(date1, date2));
    }

    @Test
    public void calcIntervalMinutes() {
        Date date1 = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        Date date2 = new GregorianCalendar(2020, Calendar.JANUARY, 2).getTime();
        logger.info("calcIntervalMinutes:{}", DateUtil.calcIntervalMinutes(date1, date2));
    }

    @Test
    public void dayOfWeek() {
        logger.info("dayOfWeek:{}", DateUtil.dayOfWeek(new Date()));
    }

    @Test
    public void getTodayMinutes() {
        logger.info("getTodayMinutes:{}", DateUtil.getTodayMinutes());
    }

    @Test
    public void stringParseDate() {
        logger.info("stringParseDate:{}", DateUtil.stringParseDate("2020/01/02"));
        logger.info("stringParseDate:{}", DateUtil.stringParseDate("2020-01-02 12:00:01"));
        logger.info("stringParseDate:{}", DateUtil.stringParseDate("2020年01月03日"));
        logger.info("stringParseDate:{}", DateUtil.stringParseDate("2020年01月03日12时00分01秒"));
        logger.info("stringParseDate:{}", DateUtil.stringParseDate("01月03日12时"));
    }

    @Test
    public void dateToString() {
        logger.info("dateToString:{}", DateUtil.dateToString(new Date(),"yyyy年MM月dd日HH时mm分ss秒"));
        logger.info("dateToString:{}", DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void getYesterday() {
        logger.info("getYesterday:{}", DateUtil.getYesterday());
    }

    @Test
    public void getLastWeek() {
        logger.info("getLastWeek:{}", DateUtil.getLastWeek());
    }

    @Test
    public void getLastMouth() {
        logger.info("getLastMouth:{}", DateUtil.getLastMouth());
    }

    @Test
    public void getMonthLastDay() {
        logger.info("getMonthLastDay:{}", DateUtil.getMonthLastDay(2020,2));
    }

    @Test
    public void isLeap() {
        logger.info("isLeap:{}", DateUtil.isLeap(2004));
    }

    @Test
    public void getAge() {
        Date birthDay = new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime();
        logger.info("getAge:{}", DateUtil.getAge(birthDay));
    }

}
