package com.wgw.cateringsystem.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wgw.cateringsystem.entity.Role;
import com.wgw.cateringsystem.entity.UserInf;
import com.wgw.cateringsystem.service.RoleService;
import com.wgw.cateringsystem.service.UserInfService;
import com.wgw.cateringsystem.util.RequestStreamParamUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    @Autowired
    private RoleService roleService;

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
       // List<UserInf> list = userInfService.getUserByParam(user);
        List<UserInf> list = new ArrayList<UserInf>();
        UserInf userInf = new UserInf();
        userInf.setSysId("111");
        userInf.setUserName("wgw");
        userInf.setShowName("位光伟");
        list.add(userInf);
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
    @ResponseBody
    public JSONObject saveUser(@RequestBody UserInf userInf){
        JSONObject json = new JSONObject();
        try{
            userInf.setSysId(UUID.randomUUID().toString().replace("_",""));
            Set<Role> setRole = new HashSet<Role>();
            String []roleIdAr = userInf.getRoleId();
            if(roleIdAr!=null && roleIdAr.length>0){
                for(int i=0;i<roleIdAr.length;i++){
                    setRole.add(roleService.getRoleById(roleIdAr[i]));
                }
                userInf.setSetRole(setRole);
            }

            UserInf user = userInfService.saveUserInf(userInf);
//            UserInf user = userInf;
            if(user!=null){
                JsonConfig jc = new JsonConfig();
                jc.setIgnoreDefaultExcludes(false);
                jc.setExcludes(new String[]{"setUser"});
                json.element("status",true);
                json.element("msg","新增成功");
                //json.element("data",user);
            }else{
                json.element("status",false);
                json.element("msg","新增失败");

            }


        }catch(Exception e){

        }
        return json;
    }

}
