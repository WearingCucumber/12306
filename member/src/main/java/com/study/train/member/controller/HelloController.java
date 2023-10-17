package com.study.train.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {
    @GetMapping("/hello")
    public  String hello(){
        return  "nihao  aaaaaaaaa         111111111111111111 aaaa        ";
    }
}
