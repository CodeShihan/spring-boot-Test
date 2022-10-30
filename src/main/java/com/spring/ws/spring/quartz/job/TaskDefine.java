package com.spring.ws.spring.quartz.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.client.ml.job.config.Job;
//import org.quartz.Job;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDefine {
    private String jobCode;

    private String jobName;

    private Class<? extends Job> jobClass;

    private String cronExpression;

    private Map<String, Object> jobDataMap;
}
