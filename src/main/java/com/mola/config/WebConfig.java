package com.mola.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebConfig implements WebApplicationInitializer{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //新建一个spring环境
        AnnotationConfigWebApplicationContext context=new
                AnnotationConfigWebApplicationContext();
        //注册mvc配置,相当于spring-mvc.xml
        context.register(MyMvcConfig.class);
        //注册pojo配置,自动扫描pojo
        context.register(PojoConfig.class);
        //设置servlet上下文
        context.setServletContext(servletContext);

        //新建dispatcherServlet,装入web服务器容器中
        ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcher",
                new DispatcherServlet(context));
        //
        servlet.addMapping("/");
        //
        servlet.setLoadOnStartup(1);


    }
}
