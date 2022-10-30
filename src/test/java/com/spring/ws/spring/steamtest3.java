package com.spring.ws.spring;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author shihan
 * @version 1.0
 * @description:
 * @date 2021/5/10 9:43
 */
public class steamtest3 {

    public static void main(String[] args) {
        //创建一个Stream流
        Stream<String> stream = Stream.of("美羊羊", "喜羊羊", "慢羊羊", "懒羊羊", "沸羊羊", "灰太狼", "红太狼", "小灰灰");
//        //对Stream流中的元素进行过滤，只要包含"羊羊"
//        Stream<String> streamYY = stream.filter(s -> s.contains("羊羊"));
//        //遍历Stream
//        streamYY.forEach(System.out::println);


        //count：统计
        //long count() 返回此流中元素的数量。
//        long count = stream.count();
//        System.out.println(count);

        //获取Stream流中的前5个元素
//        Stream<String> limitStream = stream.limit(5);
        //遍历
//        limitStream.forEach(System.out::println);


        //跳过前五个元素
        Stream<String> stringStream = stream.skip(5);
        //遍历
        stringStream.forEach(System.out::println);


    }
}
