package com.mola.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Random;

/**
 * server send event
 * 服务器推送技术
 */
@Controller
public class SseController {

    @Autowired
    Logger logger;

    @RequestMapping("/push")
    public void push(HttpServletResponse response, HttpServletRequest request){
        Random random=new Random();
        //服务器推送
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter printWriter=response.getWriter();
            while (true) {
                logger.info("推送给sessionId:"+request.getSession().getId());
                printWriter.println(random.nextInt());
                printWriter.flush();
                //间隔１秒
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
