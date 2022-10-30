package com.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class atomicInteger {public static void main(String[] args) {
    AtomicInteger atomicInteger = new AtomicInteger(2020);//底层用的CAS
    //期望 更新
    // public final boolean compareAndSet(int expect, int update) {
    // 如果我期望的值达到了 那么就去更新 否则 不更新 。 CAS 是CPU的并发原语
    System.out.println(atomicInteger.compareAndSet(2020, 2021));
    System.out.println(atomicInteger.get());  //2021
    atomicInteger.getAndIncrement();
    System.out.println(atomicInteger.compareAndSet(2020, 2021));
    System.out.println(atomicInteger.get()); //2022
}
}
