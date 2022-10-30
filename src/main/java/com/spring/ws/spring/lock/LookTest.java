package com.spring.ws.spring.lock;


import java.net.URL;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class LookTest {



    public static void main(String[] args) throws Exception {
        String a="a";
        ReentrantLock reentrantLock=new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.tryLock(10,TimeUnit.SECONDS);
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources("templates/index.ftl");
        String path = resources.nextElement().getPath();
        System.out.println(path);
        a = getString();
        //t(a);
        System.out.println(a);
        Integer[] num=new Integer[]{1,5,9,1,5,5,9,9,9};

        List<Integer> aa = new ArrayList<>();
        List<Integer> re = new ArrayList<>();
        int i = 0;
        for (Integer integer : num) {
            if(aa.contains(10-integer)){
                re.add(10-integer);
                aa.removeAll(re);
                re.clear();
                i++;
            }else{
                aa.add(integer);
            }
        }
        System.out.println(i);


        List<Integer> list=new ArrayList<>(Arrays.asList(num));
        int n=0;
        Iterator<Integer> iterators = list.iterator();
        while (iterators.hasNext()){
            Integer next = iterators.next();
            int value=10-next;
            if (list.contains(value)){
                iterators.remove();

                n++;
            }
        }
        System.out.println(i);


//        User user = new User();
//
//        for (int i = 1; i <= 10; i++) {
//            new Thread(() -> {
//                try {
//                    user.decr();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            },"A").start();
//        }
//        for (int i = 1; i <= 10; i++) {
//            new Thread(() -> {
//                try {
//                    user.incr();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            },"B").start();
//        }
//        FutureTask<String> futureTask = new FutureTask<>(new myThread());
//        new Thread(futureTask).start();
//        String s = futureTask.get();
//        System.out.println(s);

        //到0结束
        CountDownLatch downLatch=new CountDownLatch(10);
//        for (int i=0;i<=9;i++){
//            new Thread(()->{
//                downLatch.countDown();
//            }).start();
//        }
//        downLatch.countDown();
//
//        long count = downLatch.getCount();
//        System.out.println("---"+count);
//
//        CyclicBarrier cycl=new CyclicBarrier(8,()->{
//            System.out.println("hello");
//        });
//        ReadWriteLockTest readWriteLock=new ReadWriteLockTest();
//        for (int i=1;i<18;i++){
//            final String name=String.valueOf(i);
//            new Thread(()->{
//                readWriteLock.put(name);
//            }).start();
//        }
//
//        for (int i=1;i<18;i++){
//            final String name=String.valueOf(i);
//            new Thread(()->{
//                readWriteLock.get(name);
//            }).start();
//        }
//        //获取cpu的和数
//        int i = Runtime.getRuntime().availableProcessors();
//        System.out.println(i );
          new Thread(new lock("A","b")).start();
        new Thread(new lock("b","A")).start();
    }

    private static String getString() {
        String a;
        a="1";
        return a;
    }


    public static class lock implements Runnable{

        private String lockA;
        private String lockB;

        public lock(String a,String b){
            this.lockA=a;
            this.lockB=b;
        }

        @Override
        public void run() {

            synchronized (lockA){
                System.out.println(lockB);
                synchronized (lockB){
                    System.out.println(lockA);

                }
            }
        }
    }

    public static class ReadWriteLockTest{

        private volatile Map<String,Object> map=new HashMap<>();
        private final ReadWriteLock readWriteLock= new ReentrantReadWriteLock();


        public void put(String name){
            //独占锁   -->  写锁
            readWriteLock.writeLock().lock();
            try {
                System.out.println("开始插入："+name);
                map.put(name,name);
                System.out.println("插入成功："+name);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                readWriteLock.writeLock().unlock();
            }
        }

        public void get(String name){
            //共享锁   -->  读锁
            readWriteLock.readLock().lock();
            try {
                System.out.println("开始读取："+name);
                map.put(name,name);
                System.out.println("读取成功："+name);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                readWriteLock.readLock().unlock();
            }
        }


    }




    public static class  myThread implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "hello world";
        }
    }

    public static class User {
        private Integer number = 0;

        private Lock lock = new ReentrantLock();

        private Condition condition1 = lock.newCondition();
        private Condition condition2 = lock.newCondition();


        private void incr() throws InterruptedException {
            lock.lock();
            try {
                while (number > 0) {
                    condition1.await();
                }
                number++;
                System.out.println(Thread.currentThread().getName()+"->>"+number);
                condition2.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }

        private void decr() throws InterruptedException {
            lock.lock();
            try {
                while (number <= 0) {
                    condition2.await();
                }
                number--;

                System.out.println(Thread.currentThread().getName()+"->>"+number);
                condition1.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }



}
