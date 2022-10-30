package com.spring.ws.spring;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author shihan
 * @version 1.0
 * @description:
 * @date 2021/5/10 9:43
 */
public class steamtest2 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");

        //对list集合中的元素进行过滤，只要以张开头的元素，存储到一个新的集合
        List<String> listZhang = new ArrayList<>();
        for (String name: list
             ) {

            if(name.startsWith("张")){
                listZhang.add(name);
            }

        }
        System.out.println(listZhang);
//
        List<String> listThree = new ArrayList<>();
        for (String name: listZhang
             ) {
            if(name.length() == 3){
                listThree.add(name);
            }
        }
        System.out.println(listThree);
        for (String name : listThree) {
            System.out.println(name);
        }

        list.stream()
                .filter(name->name.startsWith("张"))//过滤以张开头的元素
                .filter(name->name.length()==3)//过滤长度为3的元素
                .forEach(System.out::println);//遍历


    }


//    //创建list集合
//    List<String> list = new ArrayList<>();
//    //使用List集合中的方法stream,把集合转化为流
//    Stream<String> stream1 = list.stream();
//
//    //创建set集合
//    Set<String> set = new HashSet<>();
//    //使用set集合中的方法stream,把集合转化为流
//    Stream<String> stream2 = set.stream();
//
//    //Map集合获取Stream流（间接获取）
//    //第一种：转化为set或Colleciton集合来获取流
//    Map<String,String> map = new HashMap<>();
//    Set<String> keySet = map.keySet();//获取所有的键
//    Stream<String> stream3 = keySet.stream();
//
//    Collection<String>values = map.values();//获取所有的值
//    Stream<String> stream4 = values.stream();
//
//    //第二种：通过entry来获取流
//    Set<Map.Entry<String, String>>entries = map.entrySet();
//    Stream<Map.Entry<String, String>>stream5 = entries.stream();
//
//    //Stream接口的静态方法of创建stream流
//    Stream<String> stream6 = Stream.of("a", "b", "c", "d");
//    Stream<Integer> stream7 = Stream.of(1,2,3,4);
//
//    // 数组获取流
//    //可变参数底层就是一个数组，所以也可以传递数组，创建Stream流
//
//    String[] arr1 = {"a", "b", "c", "d"};
//    Stream<String> stream8 = Stream.of(arr1);
//
//    Integer[] arr2 = {1,2,3,4};
//    Stream<Integer> stream9 = Stream.of(arr2);
}
