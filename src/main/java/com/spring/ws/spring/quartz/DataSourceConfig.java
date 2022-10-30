package com.spring.ws.spring.quartz;


import com.spring.ws.spring.database.fina.DataBaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import reactor.core.scheduler.Scheduler;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Autowired
    @Qualifier(DataBaseConfig.Ws_DataSource)
    private DataSource dataSource;

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/application.yml"));
        propertiesFactoryBean.afterPropertiesSet();
        Properties object = propertiesFactoryBean.getObject();
        return object;
    }


    /**
     * 将配置文件的数据加载到SchedulerFactoryBean中
     * @return
     * @throws IOException
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        return schedulerFactoryBean;
    }

//    /**
//     * 初始化监听器
//     * @return
//     */
//    @Bean
//    public QuartzInitializerListener executorListener(){
//        return new QuartzInitializerListener();
//    }

    /**
     * 获得Scheduler 对象
     * @return
     * @throws IOException
     */
    @Bean
    public Scheduler scheduler() throws IOException {
//        return schedulerFactoryBean().getScheduler();
        return null;
    }



}
