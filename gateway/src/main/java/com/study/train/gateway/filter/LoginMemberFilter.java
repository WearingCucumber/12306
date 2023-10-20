package com.study.train.gateway.filter;

import com.study.train.gateway.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoginMemberFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        //排除不需要拦截的请求
        if (path.contains("/admin")
                ||path.contains("/hello")
                ||path.contains("/member/member/login")
                ||path.contains("/member/member/send-code")){
            log.info("不需要验证登录：{}",path);
            return chain.filter(exchange);
        }
        //下面使需要拦截的请求
        log.info("需要登录验证：{}",path);
        String token = exchange.getRequest().getHeaders().getFirst("token");
        log.info("会员登录验证开始");
        if (token == null || token.isEmpty()){
            log.info("token为空，不合法");
            //设置返回状态码
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        boolean validate = JwtUtil.validate(token);
        if (validate){
            log.info("token有效");
            return chain.filter(exchange);
        }else {
            log.info("token无效");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }


    }
//返回的值越小 优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
