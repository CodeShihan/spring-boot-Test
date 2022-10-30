package com.spring.ws.spring.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class
JavaClassType {
    private static final Map<Class<?>,String> javaTypeMap ;
    static {
        javaTypeMap=new HashMap<>(20);
        javaTypeMap.put(Boolean.class,"Boolean.class");
        javaTypeMap.put(Character.class,"Character.class");
        javaTypeMap.put(Integer.class,"Integer.class");
        javaTypeMap.put(Byte.class,"Byte.class");
        javaTypeMap.put(Short.class,"Short.class");
        javaTypeMap.put(Long.class,"Long.class");
        javaTypeMap.put(Float.class,"Float.class");
        javaTypeMap.put(Double.class,"Double.class");
        javaTypeMap.put(String.class,"String.class");
        javaTypeMap.put(LocalDateTime.class,"LocalDateTime.class");
        javaTypeMap.put(LocalDate.class,"LocalDate.class");
        javaTypeMap.put(LocalTime.class,"LocalTime.class");
        javaTypeMap.put(BigDecimal.class,"BigDecimal.class");
    }

    public static boolean containsKey(Class<?> cls){
        return javaTypeMap.containsKey(cls);
    }

    public static String get(Class<?> cls){
        return javaTypeMap.get(cls);
    }
}
