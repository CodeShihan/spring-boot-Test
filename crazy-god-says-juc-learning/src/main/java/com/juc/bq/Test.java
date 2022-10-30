package com.juc.bq;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
         test4();
    }
    /*
     *抛出异常
     * */
    public static void test1(){
        //参数是 队列的大小
        Queue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element());//查看队首元素是啥 a

        //IllegalStateException: Queue full 抛出异常！
       // System.out.println(blockingQueue.add("d"));
        System.out.println("=====================");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        //java.util.NoSuchElementException 抛出异常
        //System.out.println(blockingQueue.element());

        //java.util.NoSuchElementException 抛出异常！！！
      //  System.out.println(blockingQueue.remove());


    }

    /*
     *有返回值，没有异常
     * */
    public static void test2(){
        //参数是 队列的大小
        Queue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println(blockingQueue.peek());//查看队首元素是啥 a

       // System.out.println(blockingQueue.offer("d"));// false 不抛出异常
       System.out.println("=====================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println(blockingQueue.peek());//查看队首元素是啥 null

        System.out.println(blockingQueue.poll());// null 不抛出异常
    }

    /*
     *等待阻塞 （一直阻塞）
     * */
    public static void test3() throws InterruptedException {
        //参数是 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        //一直阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

       //// blockingQueue.put("d"); // 队列没有位置了，一直阻塞 main线程永远结束不了

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take()); // 没有这个元素，一直阻塞
    }

    /*
     *等待，阻塞（等待超时）
     * */
    public static void test4() throws InterruptedException {
        //参数是 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));

        blockingQueue.offer("d",2, TimeUnit.SECONDS);//等待超过2s就退出

        System.out.println("=====================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        blockingQueue.poll(2,TimeUnit.SECONDS);//等待超过两s就退出
    }
}
