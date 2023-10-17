package com.study.train.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
@Slf4j
@ComponentScan("com.study.train")
@SpringBootApplication
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GateWayApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        log.info("启动成功");
        log.info("gateway host: \t http://127.0.0.1:{}",env.getProperty("server.port"));
    }
}
