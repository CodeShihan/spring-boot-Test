package com.kwq.gaoji;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//测试线程池
public class TestPool {
    public static void main(String[] args) {
    // 1. 创建服务,擦行间线程池
    // newFixedThreadPool  参数为线程池大小
    ExecutorService service = Executors.newFixedThreadPool(10);
    //执行
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
    //关闭连接
        service.shutdown();
}

 }
class MyThread implements Runnable {
    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName());


    }

}
