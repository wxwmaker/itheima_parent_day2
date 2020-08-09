package com.itheima.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
//全局的鉴权过滤器
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    //所有的请求地址都会经过当前的过滤方法
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //首先,从请求中获取参数token
        //判断token是否存在
        //如果存在,放行
        //如果不存在.拦截.并且提示用户未授权
        System.out.println("-----------------全局过滤器MyGlobalFilter---------------------");
        //1、获取参数中的token，以及token的值
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        //2、如果token的值为空，则拦截
        if (StringUtils.isBlank(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
//返回值代表的当前的自定义过滤器的执行顺序,返回值越小顺序越靠前
    @Override
    public int getOrder() {
        return 0;
    }
}
