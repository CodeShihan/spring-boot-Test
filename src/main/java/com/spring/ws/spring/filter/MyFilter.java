package com.spring.ws.spring.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println("MyFilter1");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        //System.out.println("destroy");

    }
}
