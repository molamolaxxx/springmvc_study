package com.mola.controller;

import com.alibaba.fastjson.JSONObject;
import com.mola.pojo.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * restController
 * 使这个controller下面的方法都带上@ResponseBody
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {


    @GetMapping
    public Rest getRestJson(){

        Rest rest = new Rest();
        rest.setCode(0);
        rest.setMsg("ok");
        rest.setName("这是一个restful的api");

        return rest;
    }
}
