package com.spring.ws.spring.quartz.job;

import org.apache.commons.lang3.ObjectUtils;
import org.elasticsearch.client.ml.job.config.Job;
import org.elasticsearch.threadpool.Scheduler;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JobService {

    private final String groupName="DEFAULT_GROUP";

    @Autowired
    private Scheduler scheduler;

    public JobDetail getJobDetail(String jobCode, String description, JobDataMap jobDataMap, Class<? extends Job> jobClass) {
        return JobBuilder.newJob(jobClass)
                .withIdentity(jobCode, groupName)
                .withDescription(description)
                .setJobData(jobDataMap)
                .usingJobData(jobDataMap)
                .requestRecovery()
                .storeDurably()
                .build();
    }

    /**
     * 添加任务
     * @param define
     * @return
     * @throws Exception
     */
    public JobKey addJob(TaskDefine define) throws Exception {
        //1.定时任务 的 名字和组名
        String jobCode = define.getJobCode();
        //2.定时任务 的 元数据
        JobDataMap jobDataMap = getJobDataMap(define.getJobDataMap());
        //3.定时任务 的 描述
        String jobName = define.getJobName();
        //4.定时任务 的 逻辑实现类
        Class<? extends Job> jobClass = define.getJobClass();
        //5.定时任务 的 cron表达式
        String cron = define.getCronExpression();
        JobDetail jobDetail = getJobDetail(jobCode, jobName, jobDataMap, jobClass);
        Trigger trigger = getTrigger(jobCode, jobName, jobDataMap, cron);
        scheduler.scheduleJob(jobDetail, trigger);
        return trigger.getJobKey();
    }

    /**
     * 获取触发器
     * @param jobCode     任务代码
     * @param description  任务描述
     * @param jobDataMap   任务参数（存储数据）
     * @param cronExpression cron表达式
     * @return
     * @throws Exception
     */
    public Trigger getTrigger(String jobCode,String description,JobDataMap jobDataMap,String cronExpression) throws Exception {
        if (!CronExpression.isValidExpression(cronExpression)) {
            throw new Exception("cron表达式错误！");
        }
        return TriggerBuilder.newTrigger()
                .withIdentity(jobCode, groupName)
                .withDescription(description)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .usingJobData(jobDataMap)
                .build();
    }

    /**
     * 获取JobDataMap
     * @param map
     * @return
     */
    public JobDataMap getJobDataMap(Map<?, ?> map) {
        return map == null ? new JobDataMap() : new JobDataMap(map);
    }

    /**
     * 修改任务
     * @param define
     * @throws Exception
     */
    public void updateJob(TaskDefine define) throws Exception {
        String cronExpression = define.getCronExpression();
        if (!CronExpression.isValidExpression(cronExpression)) {
            throw new Exception( "cron表达式错误！");
        }
        TriggerKey triggerKey = new TriggerKey(define.getJobCode(), groupName);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (ObjectUtils.isEmpty(cronTrigger)){
            throw new Exception("触发器为空！jobcode:"+define.getJobCode());
        }
        JobDataMap jobDataMap = getJobDataMap(define.getJobDataMap());
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing())
                .usingJobData(jobDataMap)
                .build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 删除job
     * @param jobCode
     */
    public void deleteJob(String jobCode) throws SchedulerException {
        scheduler.deleteJob(this.getJobkey(jobCode));
    }

    /**
     * 清空所有job
     */
    public void clear() throws SchedulerException {
        scheduler.clear();
    }

    /**
     * 暂停指定任务
     * @param jobCode
     * @throws SchedulerException
     */
    public void pauseJob(String jobCode) throws SchedulerException {
        scheduler.pauseJob(this.getJobkey(jobCode));
    }

    /**
     * 取消暂停
     * @throws SchedulerException
     */
    public void resumeAll() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 取消指定job的暂停
     * @param jobCode
     * @throws SchedulerException
     */
    public void resumeJob(String jobCode) throws SchedulerException {
        scheduler.resumeJob(this.getJobkey(jobCode));
    }

    /**
     * 暂停所有任务
     * @throws SchedulerException
     */
    public void pauseAll() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 获取jobkey
     * @param jobCode  任务代码
     * @return
     */
    public JobKey getJobkey(String jobCode){
        return JobKey.jobKey(jobCode,groupName);
    }




}
