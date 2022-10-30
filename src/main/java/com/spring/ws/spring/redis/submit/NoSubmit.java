package com.spring.ws.spring.redis.submit;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoSubmit {
    int value() default 20;
}
