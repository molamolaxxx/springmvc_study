package com.mola.config;

import com.mola.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan({"com.mola.controller","com.mola.config"})
public class MyMvcConfig extends WebMvcConfigurerAdapter{
    /**
     * 配置jsp的viewResolver 用来映射路径和实际页面的位置
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        //jsp实际页面的位置
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        //设置后缀
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    //将静态路径映射到访问路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //访问路径
        registry.addResourceHandler("/assets/**")
                //静态路径
                .addResourceLocations("classpath:/assets/");

    }

    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    //MultipartResolver配置
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver=
                new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5000000);
        return multipartResolver;
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor());
    }

    //添加jsp view的controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/sse").setViewName("/sse");
        registry.addViewController("/hello").setViewName("/index");
    }

}
