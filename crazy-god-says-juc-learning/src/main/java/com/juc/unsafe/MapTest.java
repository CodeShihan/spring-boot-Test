package com.juc.unsafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// ConcurrentModificationException
public class MapTest {
    public static void main(String[] args) {
        // map 是这样用的吗？ 不是，工作中不用 HashMap 因为线程不安全
        // 默认等价于什么？  new HashMap<>(16,0.75);
        // Map<String, String> map = new HashMap<>();
        // 唯一的一个家庭作业：研究ConcurrentHashMap的原理

        //方法一： 集合类
        //   Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        //方法二： 使用JUC为我们提供的Map集合实现类
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <=1; i++) {
            new Thread(()->{
                map.put("1","1");
                System.out.println(map);
            },String.valueOf(i)).start();
        } for (int i = 1; i <=1; i++) {
            new Thread(()->{
                map.put("1","1");
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }
}
