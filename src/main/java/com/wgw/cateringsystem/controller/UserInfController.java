package com.wgw.cateringsystem.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wgw.cateringsystem.entity.UserInf;
import com.wgw.cateringsystem.service.UserInfService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date: 2018/9/26 16:17
 *
 * @Author: weiguangwei
 */

@RestController
@RequestMapping("/userInfController")
public class UserInfController {

    @Autowired
    private UserInfService userInfService;

    @RequestMapping("/getUserInf")
    public String getUserInfo(){
       UserInf userInf =  userInfService.getUserInf("111");
        return "Good Morning";
    }
    @RequestMapping("/getUserInfJson")
    public JSONObject getUserInfoJson(){
        UserInf userInf =  userInfService.getUserInf("111");
        JSONObject json = new JSONObject();
        json.element("data",userInf);
        return json;
    }
}
