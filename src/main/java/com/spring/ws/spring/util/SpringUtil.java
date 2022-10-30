package com.spring.ws.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringUtil implements ApplicationContextAware, BeanFactoryAware{

    public static ApplicationContext applicationContext;

    public static BeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public static Object getBean(String beanName, Class cla){
        return applicationContext.getBean(beanName, cla);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }

//    @PostConstruct
//    public void  init(){
//        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Persion.class);
//        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) SpringUtil.beanFactory;
//        registry.registerBeanDefinition("persion", builder.getBeanDefinition());
//        Persion persion = (Persion) SpringUtil.getBean("persion", Persion.class);
//        System.err.println(persion.getName());
//        System.err.println(SpringUtil.beanFactory.containsBean("persion"));
//    }


}
