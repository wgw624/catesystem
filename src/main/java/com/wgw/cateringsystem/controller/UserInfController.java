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

    @RequestMapping("/getUserById")
    public JSONObject getUserInfo(String userId){
        UserInf userInf =  userInfService.getUserInf(userId);
        JsonConfig jc = new JsonConfig();
        jc.setIgnoreDefaultExcludes(false);
        jc.setExcludes(new String[]{"setUser"});
        JSONObject json = new JSONObject();
        String rIds[] = new String[userInf.getSetRole().size()];
        Iterator<Role> iterator = userInf.getSetRole().iterator();
        int i=0;
        while(iterator.hasNext()){
            Role role = iterator.next();
            rIds[i++]=role.getId();
        }
        userInf.setrIds(rIds);
       // userInf.setrIds();
        json.element("data",userInf,jc);
        json.element("status",true);
        return json;
    }

    @RequestMapping("/getUserAndAllRoleById")
    public JSONObject getUserAndAllRoleById(String userId){

        List<Role> listRole = roleService.getAllRole();
        UserInf userInf =  userInfService.getUserInf(userId);
        JsonConfig jc = new JsonConfig();
        jc.setIgnoreDefaultExcludes(false);
        jc.setExcludes(new String[]{"setUser"});
        JSONObject json = new JSONObject();
        String rIds[] = new String[userInf.getSetRole().size()];
        Iterator<Role> iterator = userInf.getSetRole().iterator();
        int i=0;
        while(iterator.hasNext()){
            Role role = iterator.next();
            rIds[i++]=role.getId();
        }
        userInf.setrIds(rIds);
        // userInf.setrIds();
        json.element("data",userInf,jc);
        JsonConfig jcRole = new JsonConfig();
        jcRole.setIgnoreDefaultExcludes(false);  //设置默认忽略
        jcRole.setExcludes(new String[]{"setUser"});
        json.element("allRole",listRole,jcRole);
        json.element("status",true);

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
    @ResponseBody
    public JSONObject saveUser(@RequestBody UserInf userInf){
        JSONObject json = new JSONObject();
        try{
            userInf.setId(UUID.randomUUID().toString().replace("_",""));
            Set<Role> setRole = new HashSet<Role>();
            String []roleIdAr = userInf.getrIds();
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

    @RequestMapping("/delUserById")
    public JSONObject delUser(String id){
        boolean flag= userInfService.delUser(id);
        JSONObject json = new JSONObject();
        if(flag){
           json.element("status",1);
           json.element("msg","删除成功");
        }else{
            json.element("status",0);
            json.element("msg","删除失败");
        }
        return json;
    }
    public void saveOrUpdateUser(UserInf userInf){

    }

}
