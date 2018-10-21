package com.wgw.cateringsystem.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wgw.cateringsystem.entity.UserInf;
import com.wgw.cateringsystem.service.UserInfService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Date: 2018/9/26 16:17
 *
 * @Author: weiguangwei
 */

@RestController
@RequestMapping("/userInfController")
@CrossOrigin
public class UserInfController {

    @Autowired
    private UserInfService userInfService;

    @RequestMapping("/getUserInf")
    public JSONObject getUserInfo(){
        UserInf userInf =  userInfService.getUserInf("111");

        JSONObject json = new JSONObject();
        json.element("data",userInf);
        return json;
    }
    @RequestMapping("/getAllUserInf")
    public JSONObject getAllUserInfo(){
        List<UserInf> userInfList =  userInfService.getAllUserInf();

        JSONObject json = new JSONObject();
        json.element("data",userInfList);
        return json;
    }

    @RequestMapping(value="/login")
    @ResponseBody
    public JSONObject login(String username,String password){
        JSONObject json = new JSONObject();
        System.out.println(username+"-->"+password);
        UserInf user = new UserInf();
        user.setUserName(username);
        user.setPassword(password);
        List<UserInf> list = userInfService.getUserByParam(user);
        if(list.size()>0){
            json.element("isLogin",true);
            json.element("userInf",list.get(0));
        }else{
            json.element("isLogin",false);
        }

        return json;
    }
}
