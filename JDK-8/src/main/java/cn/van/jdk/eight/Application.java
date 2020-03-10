package cn.van.jdk.eight;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @公众号： 风尘博客
 * @Classname Application
 * @Description 启动类
 * @Date 2020/3/1 8:35 下午
 * @Author by Van
 */
@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        log.info("Application start!");
    }
}
