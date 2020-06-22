package cn.van.jdk.lambda;

import cn.van.jdk.lambda.domain.UserDomain;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ReferenceTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

// ------------------- 方法引用三种格式 start ----------------

    /**
     * 实例对象名::实例方法名
     */
    @Test
    public void instanceMethod() {
        UserDomain user = new UserDomain(1L, "Van");

        Supplier<String> sup = () -> user.getUserName();
        logger.info("result:[{}]", sup.get());

        // 等价于
        Supplier<String> supplier = user::getUserName;
        logger.info("result:[{}]", supplier.get());
    }

    /**
     * 类名::静态方法名
     */
    @Test
    public void staticMethod() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        logger.info("result:[{}]", com.compare(3, 9));

        // 等价于
        Comparator<Integer> com2 = Integer::compare;
        logger.info("result:[{}]", com2.compare(3, 9));
    }

    /**
     * 类名::实例方法名
     */
    @Test
    public void instanceMethodObject() {
        UserDomain user = new UserDomain(1L, "Van");

        Function<UserDomain, String> fun = (e) -> e.getUserName();
        logger.info("result:[{}]", fun.apply(user));

        // 等价于
        Function<UserDomain, String> fun2 = UserDomain::getUserName;
        logger.info("result:[{}]", fun2.apply(user));
    }
// ------------------- 方法引用三种格式 end ----------------

// ------------------- 构造器引用 start ----------------

    @Test
    public void object() {
        // UserDomain 中必须有一个 UserDomain(String userName) 的构造器,下同
        Function<String, UserDomain> fun = (n) -> new UserDomain(n);
        fun.apply("Van");

        // 等价于
        Function<String, UserDomain> function = UserDomain::new;
        function.apply("Van");

        // 带两个参数的构造器引用就要用BiFunction，多个参数的话，还可以自定义一个这样的函数式接口
        BiConsumer<Long, String> biConsumer = UserDomain::new;
        biConsumer.accept(1L, "Van");
    }

    /**
     * 数组引用
     */
    @Test
    public void array() {
        //传统Lambda实现
        Function<Integer, int[]> function = (i) -> new int[i];
        int[] apply = function.apply(10);
        logger.info("result:[{}]", apply.length);

        //数组类型引用实现
        function = int[]::new;
        apply = function.apply(100);
        logger.info("result:[{}]", apply.length);
    }
// ------------------- 构造器引用 end ----------------

}
