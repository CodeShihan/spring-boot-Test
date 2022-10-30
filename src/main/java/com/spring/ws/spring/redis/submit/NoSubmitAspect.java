package com.spring.ws.spring.redis.submit;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class NoSubmitAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.spring.ws.spring.redis.submit.NoSubmit)")
    public void poinCut(){

    }

    @Around("poinCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取请求地址
        StringBuffer url = request.getRequestURL();
        //获取token
        String token = request.getHeader("AuthorizationToken");
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        NoSubmit noSubmit = method.getAnnotation(NoSubmit.class);
        if (noSubmit==null){
            return joinPoint.proceed();
        }
        Object[] args = joinPoint.getArgs();
        String s = JSON.toJSONString(args);
        //获取redis中存入的json值
        String s1 =(String) redisTemplate.opsForValue().get(url + ":" + token);
        //key不存在 放行将请求参数存入redis中
        if (StringUtils.isEmpty(s1)){
            redisTemplate.opsForValue().set(url+":"+token,s,noSubmit.value(),TimeUnit.SECONDS);
            return joinPoint.proceed();
        }
         //存在 比较redis中的值
        if (s1.equals(s)){
            throw new RuntimeException("重复提交!");
        }
        return joinPoint.proceed();

    }

}
