package com.wgw.cateringsystem.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wgw.cateringsystem.entity.Role;
import com.wgw.cateringsystem.entity.UserInf;
import com.wgw.cateringsystem.service.RoleService;
import com.wgw.cateringsystem.service.UserInfService;
import com.wgw.cateringsystem.util.RequestStreamParamUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.lang.StringUtils;
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
    @ApiOperation(value="根据用户ID获取用户信息")

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="userId",value="用户ID",required = true,dataType = "String"),
    })
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
    @ApiOperation(value="根据用户Id 获取用户信息并且获取所有角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="userId",value = "用户ID",dataType="String",required = true,defaultValue = "538a1605-fde9-4857-b3a2-a831005643a7")
    })
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
    @ApiOperation(value="获取所有用户信息")
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
    @ApiOperation(value="用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="username" ,value="登录用户名",dataType = "String",required = true),
            @ApiImplicitParam(paramType="query",name="password",value="登录密码",dataType="String",required=true)
    })
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
    @ApiOperation(value="保存或更新用户信息")
    public JSONObject saveUser(@RequestBody UserInf userInf){
        JSONObject json = new JSONObject();
        try{
            if(StringUtils.isBlank(userInf.getId())){
                userInf.setId(UUID.randomUUID().toString().replace("_",""));
            }
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
    @ApiOperation(value="根据用户Id 删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="用户Id",paramType = "query",dataType = "String",required = true)
    })
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


}
