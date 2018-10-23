package com.wgw.cateringsystem.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wgw.cateringsystem.entity.UserInf;
import com.wgw.cateringsystem.service.UserInfService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
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
        JsonConfig jc = new JsonConfig();
        jc.setIgnoreDefaultExcludes(false);  //设置默认忽略
        jc.setExcludes(new String[]{"setUser"});
        json.element("data",userInfList,jc);
        return json;
    }

    @RequestMapping(value="/login")
    @ResponseBody
    public JSONObject login(String username,String password){
        JSONObject json = new JSONObject();
        JsonConfig jc = new JsonConfig();
        jc.setIgnoreDefaultExcludes(false);  //设置默认忽略
        jc.setExcludes(new String[]{"setUser"});
        System.out.println(username+"-->"+password);
        UserInf user = new UserInf();
        user.setUserName(username);
        user.setPassword(password);
        List<UserInf> list = userInfService.getUserByParam(user);
        if(list.size()>0){
            json.element("isLogin",true);
            UserInf usr = list.get(0);
            json.element("userInf",list.get(0),jc);
        }else{
            json.element("isLogin",false);
        }

        return json;
    }

    @RequestMapping("/saveUser")
    public void saveUser(UserInf userInf){
        JSONObject json = new JSONObject();

        try{
            UserInf user = userInfService.saveUserInf(userInf);
            if(user!=null){
                JsonConfig jc = new JsonConfig();
                jc.setIgnoreDefaultExcludes(false);
                jc.setExcludes(new String[]{"setUser"});
                json.element("status",true);
                json.element("msg","新增成功");
                json.element("data",user);
            }else{
                json.element("status",false);
                json.element("msg","新增失败");
               
            }


        }catch(Exception e){

        }

    }
}
