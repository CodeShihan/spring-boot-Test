package com.spring.ws.spring.lock;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import javax.xml.bind.PrintConversionEvent;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class ReentrantLockTest  {

//    @Override
//    public void setBeanName(String s) {
//        System.err.println(s);
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.err.println("afterPropertiesSet");
//    }
//
//    @Override
//    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        System.err.println(beanClass);
//        System.err.println(beanName);
//        return null;
//    }
//
//    @Override
//    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
//        System.err.println(bean);
//        System.err.println(beanName);
//        return false;
//    }
}



class Test {



    public static synchronized void sm(){
        System.out.println("发短信");

    }

    public static synchronized void call() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("打电话");
    }







    private static Integer num = 0;

    private Integer i = 100;
    //不加参数
    private static Lock lock = new ReentrantLock();

    public static void incr() {
        lock.lock();
        try {
            num++;

            System.out.println(Thread.currentThread().getName() + "--->" + num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void decr() {
        i--;
        System.out.println(Thread.currentThread().getName() + "--->" + i);

    }


    static class b{
        private ReentrantLock lock =new ReentrantLock();
        private Condition condition1=lock.newCondition();
        private Condition condition2=lock.newCondition();
        private Condition condition3=lock.newCondition();


        private void A() throws InterruptedException {
            lock.lock();
            try {
                System.out.println("A");
                condition1.await();
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        }

        private void B() throws InterruptedException {
            lock.lock();
            try {
                System.out.println("B");
                condition2.await();
            }catch (Exception e){

            }finally {
                lock.unlock();
            }

        }


        private void C() throws InterruptedException {
            lock.lock();
            try {
                System.out.println("C");
                condition3.await();
            }catch (Exception e){

            }finally {
                lock.unlock();
            }

        }

    }

    public static void main(String[] args) {
        b b = new b();
        for (int i=1;i<=3;i++){
            new Thread(()->{
                try {
                    b.A();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    b.B();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    b.C();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }



    }
}
