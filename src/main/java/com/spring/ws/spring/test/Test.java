package com.spring.ws.spring.test;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public  class Test {


    static ThreadLocal<String> t = new ThreadLocal<>();
    static {
    }
    public  static void main(String[] args) {
//        t.set("name");
//        for (int i=0;i<=10;i++){
//            new Thread(()->{
//                ThreadLocal<String> t = new ThreadLocal<>();
//            }).start();
//
//        }

        String lockA = "lockA";
        String lockB = "lockB";
        String lockC = "lockC";
        String lockD = "lockD";
//        MyThread myThread = new MyThread(lockA, lockB);
//        MyThread myThread1 = new MyThread(lockC, lockD);
//        System.out.println(myThread==myThread1);
//        new Thread(new MyThread(lockA, lockB), "T1").start();
//        new Thread(new MyThread(lockC, lockD), "T2").start();

    }

    static class Phone{
        public synchronized void sms(){
            System.out.println(Thread.currentThread().getName() + "sms");
            call(); // 这里也有锁
        }
        public synchronized void call(){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "call");

        }
    }






    public static DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//    public static void main(String[] args) throws Exception {
//        List<String> strList = getBetween("2020-01","2021-11",2);
//        for (String s : strList) {
//            System.out.println(s);
//        }
//    }
    /**
     * 获取日期期间段中所有的月份
     * @param minDate
     * @param maxDate
     * @return
     * @throws Exception
     */
    public static List<String> getBetween(String minDate, String maxDate,int type) throws Exception
    {
        ArrayList<String> result = new ArrayList<String>();

        SimpleDateFormat sdf;
        if (type==2){
            sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
        }else {
            sdf = new SimpleDateFormat("yyyy");// 格式化为年月
        }
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(sdf.parse(minDate));
        max.setTime(sdf.parse(maxDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);// 设置年月日，最少3个参数
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH),2);
        Calendar curr = min;
        while (curr.before(max))
        {
            result.add(sdf.format(curr.getTime()));
            if (type==2){
                curr.add(Calendar.MONTH, 1);
                continue;
            }
            curr.add(Calendar.YEAR,1);
        }
        return result;
    }
}

interface a{
    abstract void c();
}

class b implements a{

    a a;
    public b(a a){
        this.a=a;
    }

    @Override
    public void c() {

    }
}

class d extends b{

    public d(com.spring.ws.spring.test.a b) {
        super(b);
        int i=1;
        Integer integ;
        integ=i;
//        Proxy.newProxyInstance(b.getClass().getClassLoader(), new ClassUtils.Interfaces[]{a}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return null;
//            }
//        })
        new T1(()-> {
        });
    }



    private void d(){
        super.c();
    }
}