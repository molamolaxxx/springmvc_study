package com.mola;

import com.mola.config.MyMvcConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

/**
 * @Author: molamola
 * @Date: 19-5-31 下午5:27
 * @Version 1.0
 * springmvc+内嵌tomcat模拟springboot
 */
@Slf4j
public class SpringApplication {

    private static String PROJECT_PATH = "/mvc";

    public static void run(){
        //临时目录
        File file = new File(System.getProperty("java.io.tmpdir"));

        //启动内嵌tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8888);
        tomcat.addContext(PROJECT_PATH,file.getAbsolutePath());

        //告诉tomcat是个标准的webapp项目
        //tomcat.addWebapp("/",file.getAbsolutePath());

        try {
            //启动tomcat
            tomcat.start();
            //启动web项目
            //新建一个spring环境
            AnnotationConfigWebApplicationContext springContext=new AnnotationConfigWebApplicationContext();
            //注册mvc配置,相当于spring-mvc.xml
            springContext.register(MyMvcConfig.class);

            DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);
            //在tomcat中添加dispatcherServlet
            Wrapper wrapper = tomcat.addServlet(PROJECT_PATH,"dispatcher",dispatcherServlet);

            wrapper.addMapping("/");
            wrapper.setLoadOnStartup(1);

            log.info("【tomcat】启动tomcat服务器");
            log.info("【tomcat】端口为:{}",tomcat.getConnector().getPort());

            //让tomcat阻塞
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            log.error("【tomcat】启动tomcat服务器失败");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SpringApplication.run();
    }
}
