package com.kwq.StaticProxy;

public class StaticProxy {
    public static void main(String[] args) {
        new Thread(()->System.out.println("我爱你")).start();
        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.happyMarry();
    }
}

//结婚
interface Marry {
    void happyMarry();
}

//真实角色:你去结婚
class You implements Marry {
    @Override
    public void happyMarry() {
        System.out.println("Bean要结婚了,超开心");
    }
}

//代理角色:帮助你结婚
class WeddingCompany implements Marry {
    private Marry target;//代理谁--》真实目标角色

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        this.target.happyMarry();//这就是真实对象
        after();
    }

    private void after() {
        System.out.println("结婚之后,收尾款");
    }

    private void before() {
        System.out.println("结婚之前,布置现场");
    }
}

