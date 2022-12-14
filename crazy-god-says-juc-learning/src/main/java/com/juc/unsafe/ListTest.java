package com.juc.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
        //并发ArrayList 不安全的
        /*
        * 解决方案：
        * 1.List<String> list = new Vector<>();
        * 2、List<String> list = Collections.synchronizedList(new ArrayList<>());
        * 3、List<String> list = new CopyOnWriteArrayList<>()；
        * */
     //   List<String> list = new Vector<>();
     // List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i <=10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            }).start();
        }

    }
}
