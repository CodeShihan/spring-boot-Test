package com.kwq.demo1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习Thread，实现多线程同步下载图片
public class TestThread2 implements Runnable {
    private String url;//网络图片地址
    private String name;//报错扥文件名

    //有参构造
    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为:" + name);
    }

    public static void main(String[] args) {


        TestThread2 t1= new TestThread2("https://img-home.csdnimg.cn/images/20201124032511.png", "1.png");
        TestThread2 t2 = new TestThread2("https://img-home.csdnimg.cn/images/20201124032511.png", "2.png");
        TestThread2 t3 = new TestThread2("https://img-home.csdnimg.cn/images/20201124032511.png", "3.png");
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
}
 }



//下载器
class WebDownloader{
    //下载方法
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}
