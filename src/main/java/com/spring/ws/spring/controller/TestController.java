package com.spring.ws.spring.controller;

//import com.spring.ws.spring.quartz.job.BaseJob;
import com.spring.ws.spring.quartz.job.JobService;
import com.spring.ws.spring.quartz.job.TaskDefine;
//import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private JobService jobService;

//    @RequestMapping(value = "/addJob/{jobCode}", method = RequestMethod.GET)
//    public void addJob(@PathVariable("jobCode") String jobCode) throws Exception {
//        System.err.println("添加定时任务");
//        Map<String,Object> objectMap=new HashMap<>(2);
//        objectMap.put("version","1.0.0.1");
//        objectMap.put("runStr","是是是");
//        TaskDefine taskDefine = TaskDefine.builder()
//                .jobCode(jobCode)
//                .jobName("测试任务" + jobCode)
//                .cronExpression("0/2 * * * * ? ")
//                .jobClass(BaseJob.class)
//                .jobDataMap(objectMap)
//                .build();
//        jobService.addJob(taskDefine);
//    }
//
//    @RequestMapping(value = "/update/{jobCode}", method = RequestMethod.GET)
//    public void updateJob(@PathVariable("jobCode") String jobCode) throws Exception {
//        System.err.println("修改定时任务");
//        Map<String,Object> objectMap=new HashMap<>(2);
//        objectMap.put("version","1.0.0.1");
//        objectMap.put("runStr","是是是");
//        TaskDefine taskDefine = TaskDefine.builder()
//                .jobCode(jobCode)
//                .jobName("修改" + jobCode)
//                .cronExpression("0/10 * * * * ? ")
//                .jobClass(BaseJob.class)
//                .jobDataMap(objectMap)
//                .build();
//        jobService.updateJob(taskDefine);
//    }
//
//    @RequestMapping(value = "/pauseJob/{jobCode}", method = RequestMethod.GET)
//    public void pauseJob(@PathVariable String jobCode) throws Exception {
//        System.err.println("暂停定时任务");
//
//        jobService.pauseJob(jobCode);
//    }
//
//    @RequestMapping(value = "/resumeAll", method = RequestMethod.GET)
//    public void resumeAll() throws Exception {
//        System.err.println("回复定时任务");
//
//        jobService.resumeAll();
//    }
//
//
//    @RequestMapping(value = "/clear", method = RequestMethod.GET)
//    public void clearJob() throws Exception {
//        System.err.println("清理定时任务");
//
//        jobService.clear();
//    }
//
//    @RequestMapping(value = "/delete/{jobCode}", method = RequestMethod.GET)
//    public void deleteJob(@PathVariable("jobCode") String jobCode) throws Exception {
//        System.err.println("删除定时任务");
//
//        jobService.deleteJob(jobCode);
//    }


}
