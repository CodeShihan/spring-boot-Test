package com.kwq.gaoji;
//测试：生产者消费者模型--> 利用缓冲区解决：管程法
// 生产者，消费者，产品，缓冲区
public class TestPC {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        new Producer(synContainer).start();
        new Consumer(synContainer).start();
    }
}

//生产者
class Producer extends Thread {
    //容缓冲区
    SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    //生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Chincken(i));
            System.out.println("生产了第" + i + "只鸡");
        }
    }
}

//消费者
class Consumer extends Thread {
    //容缓冲区
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    //消费
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了第-->" + container.pop().id + "只鸡");
        }
    }
}

//产品
class Chincken {
    int id;//产品编号

    public Chincken(int id) {
        this.id = id;
    }
}

//缓冲区
class SynContainer {
    //需要一个容器大小
    Chincken[] chinckens = new Chincken[10];
    //容器计数器
    int count = 0;

    //生产者放入产品
    public synchronized void push(Chincken chincken) {
        //如果容器满了,需要等待消费者消费
        if (count==chinckens.length){
            //通知消费者消费，生产等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没有满,需要丢入产品
        chinckens[count]=chincken;
        count++;
        //可以通知消费者消费
        this.notifyAll();

    }

    //消费者消费产品
    public synchronized Chincken pop() {
        //判断是否能消费
        if (count <= 0) {
            //等待生产者生产,消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //如果可以消费
        count--;
        Chincken chincken= chinckens[count];
        //吃完了 通知生产者生产
        this.notifyAll();
        return chincken;
    }

}

