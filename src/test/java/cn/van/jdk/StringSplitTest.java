package cn.van.jdk;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @公众号： 风尘博客
 * @Classname StringTest
 * @Description 字符串分割
 * @Date 2020/3/1 19:30 下午
 * @Author by Van
 */
public class StringSplitTest {

    @Test
    public void stringSplit() {
        // 正常情况
        String string = "风尘-博客";
        String[] arr = string.split("-");
        String part1 = arr[0];
        System.out.println(part1);
        String part2 = arr[1];
        System.out.println(part2);

        // 会出问题
        String str = "风尘.博客";
        String[] strArr = str.split(".");
        String string1 = strArr[0];
        System.out.println(string1);
        String string2 = strArr[1];
        System.out.println(string2);
    }

    /**
     * 转义字符，必须得加 \\ 或者使用 Pattern.quote()
     */
    @Test
    public void trueMethod() {
        String str = "风尘.博客";
        String[] strArr = str.split("\\.");
        // 这一种写法也行
//        String[] strArr = str.split(Pattern.quote("."));
        String string1 = strArr[0];
        System.out.println(string1);
        String string2 = strArr[1];
        System.out.println(string2);
    }

    /**
     * 多个分隔符，可以用 | 作为连字符
     */
    @Test
    public void upMethod() {
        String str = "风尘.博客|你好";
        String[] strArr = str.split("\\.|\\|");
        String string1 = strArr[0];
        System.out.println(string1);
        String string2 = strArr[1];
        System.out.println(string2);
        String string3 = strArr[2];
        System.out.println(string3);
    }
}
