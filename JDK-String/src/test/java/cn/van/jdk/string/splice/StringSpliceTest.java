package cn.van.jdk.string.splice;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @公众号： 风尘博客
 * @Classname StringSpliceTest
 * @Description 字符串拼接
 * @Date 2020/3/01 4:18 下午
 * @Author by Van
 */
public class StringSpliceTest {

    protected Logger logger= LoggerFactory.getLogger(getClass());

    /**
     * 使用`+`拼接字符串
     */
    @Test
    public void plus() {
        String str = "风尘";
        String weChat = "博客";
        String string = str + weChat;
        logger.info(string);
    }

    /**
     * concat方式
     */
    @Test
    public void concat() {
        String str = "风尘";
        String weChat = "博客";
        String string = str.concat(weChat);
        logger.info(string);
        // java.lang.NullPointerException
        str.concat(null);
    }

    /**
     * StringBuffer.append()
     */
    @Test
    public void stringBuffer() {
        StringBuffer str = new StringBuffer("欢迎关注");
        StringBuffer string = str.append(":").append("风尘博客");
        logger.info(string.toString());
    }

    /**
     * StringBuilder.append()
     */
    @Test
    public void stringBuilder() {
        StringBuilder str = new StringBuilder("欢迎关注");
        StringBuilder string = str.append(":").append("风尘博客");
        logger.info(string.toString());
    }

    /**
     * StringJoiner.add()
     */
    @Test
    public void stringJoiner() {
        StringJoiner str = new StringJoiner(":");
        str.add("欢迎关注").add("风尘博客");
        logger.info(str.toString());
    }

    /**
     * 集合进行字符串拼接
     */
    @Test
    public void listSplice() {
        List<String> list = new ArrayList<>();
        list.add("欢迎关注");
        list.add("风尘博客");
        String str = list.stream().collect(Collectors.joining(":"));
        logger.info(str);
    }

    /**
     * String 类的 join 方法
     */
    @Test
    public void join() {
        // 第一个参数为字符串连接符。
        String str = String.join(":","欢迎关注","风尘博客");
        logger.info(str);
    }
}
