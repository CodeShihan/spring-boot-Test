package com.kwq.demo1;

public class TestThread3 implements Runnable {
    @Override
    public void run() {
        //run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码----" + i);
        }
    }

    public static void main(String[] args) {
        //创建runnable接口的实现类对象
        TestThread3 testThread = new TestThread3();
        //创建线程对象,通过线程对象来开启我们的线程,代理
        Thread thread = new Thread(testThread);
        //调用start（）开启线程
        thread.start();

        //new Thread(testThread).start();
        for (int i = 0; i < 2000; i++) {
            System.out.println("我在学习多线程----" + i);
        }
    }
}

