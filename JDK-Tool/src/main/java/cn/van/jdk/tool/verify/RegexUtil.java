package cn.van.jdk.tool.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: RegexUtils
 *
 * @author: Van
 * Date:     2020-06-10 22:17
 * Description: 正则相关工具类
 * Version： V1.0
 */
public class RegexUtil {

    /**
     * 私有化构造器
     */
    private RegexUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }


    /**
     * 获取正则匹配的部分
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return 正则匹配的部分
     */
    public static String getMatches(String regex, CharSequence input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        StringBuffer matches = new StringBuffer();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.append(matcher.group());
        }
        return matches.toString();
    }

    /**
     * 获取正则匹配的部分
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return 正则匹配的部分
     */
    public static List<Object> getMatchesList(String regex, CharSequence input) {
        Pattern pattern = Pattern.compile(regex);
        if (input == null || input.length() == 0) {
            return null;
        }
        List<Object> matches = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            if (!matcher.group().isEmpty()) {
                matches.add(matcher.group());
            }
        }
        return matches;
    }

    /**
     * 计算指定字符串中，匹配 pattern 的个数
     *
     * @param regex 正则表达式
     * @param input 被查找的内容
     * @return 匹配个数
     */
    public static int count(String regex, CharSequence input) {
        if (null == regex || input == null || input.length() == 0) {
            return 0;
        }
        Pattern pattern = Pattern.compile(regex);
        int count = 0;
        final Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            if (!matcher.group().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    /**
     * 替换所有正则匹配的部分
     *
     * @param input       要替换的字符串
     * @param regex       正则表达式
     * @param replacement 代替者
     * @return 替换所有正则匹配的部分
     */
    public static String replaceAll(String regex, String input, String replacement) {
        if (input == null || input.length() == 0) {
            return "";
        }
        return Pattern.compile(regex).matcher(input).replaceAll(replacement);
    }


    /**
     * 替换正则匹配的第一部分
     *
     * @param input       要替换的字符串
     * @param regex       正则表达式
     * @param replacement 代替者
     * @return 替换正则匹配的第一部分
     */
    public static String replaceFirst(String regex, String input, String replacement) {
        if (input == null || input.length() == 0) {
            return "";
        }
        return Pattern.compile(regex).matcher(input).replaceFirst(replacement);
    }

    /**
     * 删除(替换)正则匹配到的内容之前的字符，如果没有找到，则返回原文
     *
     * @param regex 定位正则
     * @param input 被查找的内容
     * @return 删除前缀后的新内容
     */
    public static String delPre(String regex, CharSequence input) {
        if (regex == null || input == null || input.length() == 0) {
            return "";
        }
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return input.toString().substring(matcher.end(), input.length());
        }
        return input.toString();
    }

    /**
     * 转义字符串，将正则的关键字转义
     *
     * @param input 文本
     * @return 转义后的文本
     */
    public static String escape(CharSequence input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        final StringBuilder builder = new StringBuilder();
        int len = input.length();
        char current;
        for (int i = 0; i < len; i++) {
            current = input.charAt(i);
            if (RegexConstant.RE_KEYS.contains(current)) {
                builder.append('\\');
            }
            builder.append(current);
        }
        return builder.toString();
    }
}
