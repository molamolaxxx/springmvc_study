package com.mola.controller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class MyController {

    private static String FILE_SAVE_PATH="/home/molamola/IdeaProjects/springmvc_study/src/main/resources/files/";

    @Autowired
    Logger logger;

    //上传文件
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file){

        try {
            String fileName=new String(file.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");

            logger.info(fileName);
            //通过文件流传输上传的文件
            FileUtils.writeByteArrayToFile(new File(FILE_SAVE_PATH+fileName)
                    ,file.getBytes());

            return "success";
        } catch (Exception e) {

            e.printStackTrace();
            return "false";
        }
    }
}
