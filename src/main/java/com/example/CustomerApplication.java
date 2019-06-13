package com.example;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "com.example",exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo(scanBasePackages = "com.example")
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication s = new SpringApplication(CustomerApplication.class);
        s.setBannerMode(Banner.Mode.OFF);
        s.run(args);
    }
}
