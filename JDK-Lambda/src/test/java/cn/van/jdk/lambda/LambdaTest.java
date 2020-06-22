package cn.van.jdk.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;


public class LambdaTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 传统的写法
     */
    @Test
    public void general() {
        // 用匿名内部类的方式来创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("公众号：风尘博客！");
            }
        }).run();
    }

    /**
     * Lambda 写法
     */
    @Test
    public void lambda() {
        // 使用Lambda来创建线程
        new Thread(() -> logger.info("公众号：风尘博客！")).run();
    }


    /**
     * 无参，无返回值，Lambda 体只需一条语句。
     */
    @Test
    public void noParam() {
        Runnable r1 = () -> logger.info("noParam Test!");
        r1.run();
    }

    /**
     * Lambda 需要一个参数
     */
    @Test
    public void oneParam() {
        // Consumer<String> con = (s) -> logger.info(s);
        // 参数的小括号可以省略。
        Consumer<String> con = s -> logger.info(s);
        con.accept("oneParam Test!");
    }

    /**
     * Lambda 需要多个参数，并且有返回值。
     */
    @Test
    public void params() {
        Comparator<Integer> com = (x, y) -> {
            logger.info("params Test!");
            // 比较x/y的大小
            return Integer.compare(x, y);
        };
        logger.info("result:[{}]", com.compare(1, 2));
    }

    /**
     * 当 Lambda 体只有一条语句时，return 与大括号可以省略。
     */
    @Test
    public void one() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        logger.info("result:[{}]", com.compare(1, 2));
    }

    /**
     * 类型推断
     */
    @Test
    public void typeInference() {
        //Integer 类型可以省略
        Comparator<Integer> com = (Integer x, Integer y) -> {
            logger.info("函数式接口");
            return Integer.compare(x, y);
        };
        // 类型推断
        BinaryOperator<Long> addImplicit = (x, y) -> x + y;
    }
}
