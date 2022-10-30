package com.kwq.syn;

import java.util.ArrayList;
import java.util.List;

//线程不安全的集合
public class UnsafeList {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                synchronized (list){
                list.add(Thread.currentThread().getName());}
            }).start();
        }
        Thread.sleep(300);
        System.out.println(list.size());

    }
}

