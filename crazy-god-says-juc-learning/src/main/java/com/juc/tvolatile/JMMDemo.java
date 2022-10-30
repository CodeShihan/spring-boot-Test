//package com.juc.tvolatile;
//
//import java.util.concurrent.TimeUnit;
//
//public class 0JMMDemo {
//    // 不加xolitile 程序就会死循环
//    // 加volatile 就可以保证可见性
////    private  static int num = 0;
//
//    public static void main(String[] args) { // main
//
//        new Thread(()->{ // 线程 1 对主内存的变化不知道的
//            while (num==0){
//
//            }
//        }).start();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        num = 1;
//        System.out.println(num);
//
//    }
//}
