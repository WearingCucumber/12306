package com.study.train.member.config;

import com.study.train.common.interceptor.MemberInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Autowired
    MemberInterceptor memberInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/member/member/send-code",
                        "/member/member/login"
                );
    }
}
