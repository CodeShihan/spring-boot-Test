package com.juc.demo1;

// 基本的卖票例子


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 真正的多线程开发，公司中的开发，降低耦合性
 * 线程就是一个单独的资源类，没有任何附属的操作！
 * 1、 属性、方法
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        // 并发：多线程操作同一个资源类, 把资源类丢入线程
        Ticket2 ticket = new Ticket2();

        // @FunctionalInterface 函数式接口，jdk1.8  lambda表达式 (参数)->{ 代码 }
        new Thread(()->{ for (int i = 1; i < 10 ; i++) ticket.sale(); },"A").start();
        new Thread(()->{ for (int i = 1; i < 10 ; i++) ticket.sale(); },"B").start();
        new Thread(()->{ for (int i = 1; i < 10 ; i++) ticket.sale(); },"C").start();
        new Thread(()->{ for (int i = 1; i < 10 ; i++) ticket.sale(); },"D").start();


    }
}

// 资源类 OOP
class Ticket2 {
    // 属性、方法
    private int number = 10;
   //声明锁（可重入锁）
    Lock lock=new ReentrantLock();

    // 卖票的方式

    public  void sale(){
        lock.lock();
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了第"+(number--)+"票,剩余："+number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
