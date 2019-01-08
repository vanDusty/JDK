package cn.van;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class RegretApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegretApplication.class, args);
    }

    @GetMapping("/demo")
    public String demo() {
        return "Hello Luis";
    }
}

