package com.spring.ws.spring.filter.config;

import com.spring.ws.spring.filter.MyFilter;
import com.spring.ws.spring.filter.MyFilter2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

    @Autowired
    private MyFilter myFilter;
    @Autowired
    private MyFilter2 myFilter2;

    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean registration=new FilterRegistrationBean();
        registration.setFilter(myFilter);
        registration.addUrlPatterns("/*");
        registration.setName("myFilter");
        registration.setOrder(3);
        return registration;

    }

    @Bean
    public FilterRegistrationBean registration(){
        FilterRegistrationBean registration=new FilterRegistrationBean();
        registration.setFilter(myFilter2);
        registration.addUrlPatterns("/*");
        registration.setName("myFilter2");
        //加载的优先级
        registration.setOrder(2);
        return registration;

    }
}
