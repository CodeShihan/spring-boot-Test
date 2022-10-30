package com.kwq.syn;
//不安全的取钱
//两个人去银行取钱，账户
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");
        Drawing you = new Drawing(account, 50, "你");
        Drawing girlfriend = new Drawing(account, 100, "girlFriend");
        you.start();
        girlfriend.start();
    }
}

//账户
class Account {
    int money;//余额
    String cardName;//卡名

    public Account(int money, String cardName) {
        this.money = money;
        this.cardName = cardName;
    }
}

//银行:模拟取款
class Drawing extends Thread {
    Account account;//账户
    int drawingMoney;//取金额
    int nowMoney;//你手里的钱

    public Drawing(Account account, int drawingMoney, String name) {
        //调用父类的方法
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;

    }

    //取钱  synchronized默认锁的是this
    @Override
    public  void run() {
       //锁的对象就是变化的量，需要cud的操作
        synchronized (account) {
            //判断是否有钱
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "余额不足,不能进行取钱");
                return;
            }
            try {
                Thread.sleep(1000);//放大问题的发生性
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //卡内金额 = 余额-你的钱
            account.money = account.money - drawingMoney;
            //你手里的钱
            nowMoney = drawingMoney;
            System.out.println(account.cardName + "余额为:" + account.money);
            //this.getName()==super.getName()==Thread.currentThread().getName()
            System.out.println(super.getName() + "手里的钱:" + nowMoney);
        }
    }
}
