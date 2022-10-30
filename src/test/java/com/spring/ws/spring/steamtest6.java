package com.spring.ws.spring;

import net.minidev.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author shihan
 * @version 1.0
 * @description:
 * @date 2021/5/10 9:43
 */
public class steamtest6 {

    public static void main(String[] args) {
        List<String> one = new ArrayList<>();
        one.add("迪丽热巴");
        one.add("宋远桥");
        one.add("李星云");
        one.add("苏星河");
        one.add("老子");
        one.add("庄子");
        one.add("孙子");
        one.add("洪七公");



        //1. 第一个队伍只要名字为3个字的成员姓名；
        //2.  第一个队伍筛选之后只要前3个人；
        //把集合转化成流
        Stream<String> oneStream =  one.stream().filter(name ->name.length() ==3).limit(3);
        oneStream.forEach(System.out::println);


        List<String> two = new ArrayList<>();
        two.add("古力娜扎");
        two.add("张无忌");
        two.add("张三丰");
        two.add("赵丽颖");
        two.add("张二狗");
        two.add("张天爱");
        two.add("张三");
        //3.  第二个队伍只要姓张的成员姓名；
        //4.  第二个队伍筛选之后不要前2个人；
        //把集合转化为Stream流
        Stream<String> twoStream = two.stream().filter(name -> name.startsWith("张")).skip(2);
        twoStream.forEach(System.out::println);

//        //5.  将两个队伍合并为一个队伍；
//        //6. 根据姓名创建 Person 对象；
//        //7. 打印整个队伍的Person对象信息。
        Stream.concat(oneStream,twoStream).map(Person::new).forEach(System.out::println);
//


    }
}
