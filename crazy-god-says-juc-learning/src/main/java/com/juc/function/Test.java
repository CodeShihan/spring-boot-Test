package com.juc.function;

// 匿名内部类实现方法
public class Test {
    public static void main(String[] args) {
        MyInterface test = new MyInterface() {
            @Override
            public void method() {
                System.out.println();
            }
        };
        test.method();

        MyInterface t = () -> System.out.println();
        t.method();
    }
}
