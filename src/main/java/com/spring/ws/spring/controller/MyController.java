package com.spring.ws.spring.controller;

import com.spring.ws.spring.redis.RedisLockUtil;
import com.spring.ws.spring.redis.RedisUtil;
import com.spring.ws.spring.response.BaseDTOUtil;
import com.spring.ws.spring.response.BaseResponse;
import com.spring.ws.spring.service.UserService;
import com.spring.ws.spring.sql.ConDataSource;
import com.spring.ws.spring.sql.DbProperty;
import com.spring.ws.spring.sql.FiledGenerate;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@Api(value = "Test",tags="Test")
@Slf4j
public class MyController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisLockUtil redisLockUtil;

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private String port;

    @Autowired
    private FiledGenerate filedGenerate;

    @RequestMapping(value = "/t6",method = RequestMethod.GET)
    @ApiOperation("t")
    private String t6() throws Exception {
//        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
//        stringTemplateLoader.putTemplate("index","");

        filedGenerate.generate();
        return "生成成功";
    }

    public static void main(String[] args) {

    }














    @RequestMapping(value = "/t5",method = RequestMethod.GET)
    @ApiOperation("t")
    private BaseResponse t5() throws Exception {
        DbProperty dbProperty=new DbProperty();
        dbProperty.setDbName("ws");
        dbProperty.setPassword("123456");
        dbProperty.setUserName("root");
        dbProperty.setTableName("user");
        dbProperty.setUrl("jdbc:mysql://121.5.73.211:3306/ws?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
        ConDataSource conDataSource=new ConDataSource(dbProperty);
        conDataSource.getFileds();
        return null;
    }


//    @RequestMapping(value = "/nginx",method = RequestMethod.GET)
//    private String nginxTest() throws InterruptedException {
//        return port;
//    }

    @RequestMapping(value = "/t3",method = RequestMethod.GET)
    @ApiOperation("t")
    private BaseResponse t3() throws InterruptedException {
        return userService.selectOne(1);
    }


    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);


    @SneakyThrows
    @RequestMapping(value = "/t2",method = RequestMethod.GET)
    @ApiOperation("t")
    private BaseResponse t2() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1024";
        });
        
        String s = future.get();
        return BaseDTOUtil.getBaseResponseSuccess(s);
    }


    @RequestMapping(value = "/t1",method = RequestMethod.GET)
    @ApiOperation("t")
    private BaseResponse t1() throws InterruptedException {
        for (int i=0;i<200;i++){
            threadPool.submit(() -> {
                try {
                    t();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        return BaseDTOUtil.getBaseResponseSuccess("Test");
    }


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ApiOperation("test")
    private BaseResponse<String> test() throws InterruptedException {
        for (int i=0;i<200;i++){
            threadPool.submit(() -> {
                try {
                    t();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        return BaseDTOUtil.getBaseResponseSuccess("Test");
    }


    public void t() throws InterruptedException {
        try {
            while (redisLockUtil.lock("keyLock",30)){
                log.info("获取锁-----"+Thread.currentThread().getName());
                Long num = Long.parseLong(redisUtil.get("num"));
                while (num>0){
                    log.info("扣减库存-----"+Thread.currentThread().getName());
                    redisUtil.incrBy("num",-1);
                    return;
                }
                log.info("库存扣减失败-----"+Thread.currentThread().getName());
                return;
            }
            log.info("等待重试-----"+Thread.currentThread().getName());
            Thread.sleep(500);
            t();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            log.info("释放锁-----"+Thread.currentThread().getName());
            redisLockUtil.unlock("keyLock");
        }
    }




}
