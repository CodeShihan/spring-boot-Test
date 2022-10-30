package com.spring.ws.spring;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shihan
 * @version 1.0
 * @description:
 * @date 2021/5/10 9:43
 */
public class steamtest5 {

    public static void main(String[] args) {

//          //创建一个Stream1流
//        Stream<String> stream1 = Stream.of("张三", "李四", "王五", "赵六", "田七");
//        //创建一个Stream2流
//        Stream<String> stream2 = Stream.of("美羊羊", "喜羊羊", "慢羊羊", "懒羊羊", "沸羊羊", "灰太狼", "红太狼", "小灰灰");
//        //使用Stream中的静态方法concat，把两个流合成一个新的流
//        Stream<String> stream = Stream.concat(stream1, stream2);
//        //遍历
//        stream.forEach(System.out::println);


        Stream<String> stream = Stream.of("美羊羊", "喜羊羊", "慢羊羊", "懒羊羊", "沸羊羊", "灰太狼", "红太狼", "小灰灰");
//        List<String> list = stream.collect(Collectors.toList());
//        Set<String> set = stream.collect(Collectors.toSet());
//        System.out.println(list);
//        System.out.println(set);

        Object[] arr = stream.toArray();
        for (Object o : arr) {
            System.out.println(o);

        }
    }
}
