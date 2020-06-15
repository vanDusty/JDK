package cn.van.jdk.tool.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: BigDecimalUtil
 *
 * @author: Van
 * Date:     2019-12-14 22:15
 * Description: 科学计算工具类
 * Version： V1.0
 */
public class BigDecimalUtil {

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 10;

    // ------------------------------- 普通加减乘除 start

    /**
     * 精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(Double v1, Double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 精确的加法运算（可扩展的方法）
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被加值
     * @return 多个被加值的和
     */
    public static BigDecimal add(Number... values) {
        if (arrayIsEmpty(values)) {
            return BigDecimal.ZERO;
        }
        Number value = values[0];
        BigDecimal result = new BigDecimal(null == value ? "0" : value.toString());
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            if (null != value) {
                result = result.add(new BigDecimal(value.toString()));
            }
        }
        return result;
    }

    /**
     * 精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(Double v1, Double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 精确的减法运算（可扩展的方法）
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被减值
     * @return 多个被加值的和, 第一个数为被减数
     */
    public static BigDecimal sub(Number... values) {
        if (arrayIsEmpty(values)) {
            return BigDecimal.ZERO;
        }
        Number value = values[0];
        BigDecimal result = new BigDecimal(null == value ? "0" : value.toString());
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            if (null != value) {
                result = result.subtract(new BigDecimal(value.toString()));
            }
        }
        return result;
    }

    /**
     * 精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(Double v1, Double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 精确的乘法运算（可扩展的方法）
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被乘值
     * @return 积
     */
    public static BigDecimal mul(Number... values) {
        if (arrayIsEmpty(values)) {
            return BigDecimal.ZERO;
        }
        Number value = values[0];
        BigDecimal result = new BigDecimal(null == value ? "0" : value.toString());
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            result = result.multiply(new BigDecimal(null == value ? "0" : value.toString()));
        }
        return result;
    }

    /**
     * （相对）精确的除法运算，当发生除不尽的情况时，默认精确到小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(Double v1, Double v2) {
        return div(v1, v2, DEF_DIV_SCALE, RoundingMode.HALF_UP);
    }

    // ------------------------------- 普通加减乘除 end

    // ------------------------------- 按照精度四舍五入或者向上/向下取整 start

    /**
     * 得到计算结果后四舍五入
     *
     * @param val
     * @param scale 精度
     * @return 例如保留三位小数：0.646464 =》 0.646
     */
    public static double roundHalfUp(double val, int scale) {
        BigDecimal dec = BigDecimal.valueOf(val);
        return dec.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 得到计算结果后向上取整
     *
     * @param val   val
     * @param scale 精度
     * @return 例如保留三位小数：0.646464 =》 0.647
     */
    public static double roundUp(double val, int scale) {
        BigDecimal dec = BigDecimal.valueOf(val);
        return dec.setScale(scale, RoundingMode.UP).doubleValue();
    }

    /**
     * 得到计算结果后向下取整
     *
     * @param val   val
     * @param scale 精度
     * @return 例如保留三位小数：0.646464 =》 0.646
     */
    public static double roundDown(double val, int scale) {
        BigDecimal dec = BigDecimal.valueOf(val);
        return dec.setScale(scale, RoundingMode.DOWN).doubleValue();
    }

    /**
     * 除法运算加上向上取整。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精度
     * @return
     */
    public static double divOfUp(Double v1, Double v2, int scale) {
        return div(v1, v2, scale, RoundingMode.UP);
    }

    /**
     * 除法运算加上向下取整。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精度
     * @return
     */
    public static double divOfDown(Double v1, Double v2, int scale) {
        return div(v1, v2, scale, RoundingMode.DOWN);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由 scale 参数指定精度，roundingMode 指定取舍方式。
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        表示表示需要精确到小数点以后几位。
     * @param roundingMode 指定取舍方式。
     * @return 两个参数的商
     */
    private static double div(double v1, double v2, int scale, RoundingMode roundingMode) {
        //如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, scale, roundingMode).doubleValue();
    }

    // ------------------------------- 按照精度四舍五入或者向上/向下取整 end

    // ------------------------------- 如果正好是除以或乘以整十，start

    /**
     * 小数点向右移动指定位数
     *
     * @param val   被乘数
     * @param index 移动位数
     * @return 例如向右移动四位小数：1000.01 =》 1000010.0
     */
    public static double movePointRight(Double val, int index) {
        BigDecimal value = BigDecimal.valueOf(val);
        return value.movePointRight(index).doubleValue();
    }

    /**
     * 小数点向左移动指定位数
     *
     * @param val   被除数
     * @param index 移动位数
     * @return 例如向左移动四位小数：1000.01 =》 1.00001
     */
    public static double movePointLeft(Double val, int index) {
        BigDecimal value = BigDecimal.valueOf(val);
        return value.movePointLeft(index).doubleValue();
    }

    // ------------------------------- 如果正好是除以或乘以整十，end

    /**
     * 判断数组为空
     */
    private static <T> boolean arrayIsEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
