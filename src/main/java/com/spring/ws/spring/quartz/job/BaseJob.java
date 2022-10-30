package com.spring.ws.spring.quartz.job;

//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BaseJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.err.println("-----"+ LocalDateTime.now()+"---->"+jobExecutionContext.getTrigger().getJobKey());
    }
}
