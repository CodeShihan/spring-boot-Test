package com.spring.ws.spring;

import java.util.stream.Stream;

/**
 * @author shihan
 * @version 1.0
 * @description:
 * @date 2021/5/10 9:43
 */
public class steamtest4 {

    public static void main(String[] args) {

        //创建一个Stream流
        Stream<String> stringStream = Stream.of("1", "2", "3", "4", "5");
        //使用map方法把String类型的Stream映射为Integer类型的Stream

        Stream<Integer> integerStream = stringStream.map((String s) ->{
            return Integer.parseInt(s);
        });
        //遍历
        integerStream.forEach(System.out::println);

    }
}
