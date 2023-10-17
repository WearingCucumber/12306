package com.study.train.member.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
@Slf4j
@SpringBootApplication
@ComponentScan("com.study.train")
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        log.info("启动成功");
        log.info("test host: \t http://127.0.0.1:{}{}/test/hello",env.getProperty("server.port"),env.getProperty("server.servlet.context-path"));
    }
}
