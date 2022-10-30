package com.kwq.syn;
//不安全的买票
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket, "苦逼的我").start();
        new Thread(buyTicket, "牛逼的我们").start();
        new Thread(buyTicket, "可恶的黄牛党").start();
    }
}

class BuyTicket implements Runnable {
    //票
    private int ticketNums = 10;
    boolean flag = true;

    @Override
    public void run() {
        //买票
        while (flag) {
            try {
                buy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //买票 synchronized同步方法 锁的是this
    private synchronized void buy() {
        //判断是否有票
        if (ticketNums <= 0) {
            flag = false;//外部停止方式
            return;
        }
        //模拟延时，放大问题的发生性
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //买票
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);
    }

}
