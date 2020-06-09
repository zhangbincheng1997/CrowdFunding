package com.example.demo.base;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class LogAspect {

    // 定义切点
    @Pointcut("execution(* com.example.demo.controller.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String uri = request.getRequestURI();
        String method = request.getMethod();
        String params = JSON.toJSONString(request.getParameterMap());
        log.info("***************************************************");
        log.info("请求开始 | URI: {}, method: {}, params: {}", uri, method, params);

        Long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        Long end = System.currentTimeMillis();

        log.info("请求结束 | " + JSON.toJSONString(result));
        log.info("time: {}ms", end - start);
        return result;
    }
}