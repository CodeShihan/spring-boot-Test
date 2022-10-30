package com.juc.fill_expand;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_expand {
    private static Lock lock=new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println("aaa");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
