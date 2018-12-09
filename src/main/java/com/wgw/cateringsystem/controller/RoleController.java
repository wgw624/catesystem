package com.wgw.cateringsystem.controller;

import com.wgw.cateringsystem.entity.Role;
import com.wgw.cateringsystem.entity.UserInf;
import com.wgw.cateringsystem.service.RoleService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Date: 2018/10/23 16:28
 *
 * @Author: weiguangwei
 */
@RestController
@RequestMapping("/roleController")
@CrossOrigin
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/getAllRole")
    public JSONObject getAllRole(){
        List<Role> role = roleService.getAllRole();
        JSONObject json = new JSONObject();
        JsonConfig jc = new JsonConfig();
        jc.setIgnoreDefaultExcludes(false);
//        jc.setExcludes(new String[]{"roleInf"});
        jc.setExcludes(new String[]{"setUser"});
        if(role.size()>0){
            json.element("status",true);
            json.element("role",role,jc);
        }else{
            json.element("status",false);
            json.element("msg","没有查到角色信息");
        }
        return json;
    }
    @RequestMapping("/saveRole")
    public JSONObject saveRole(@RequestBody Role role){
        if(StringUtils.isBlank((role.getId()))){
            role.setId(UUID.randomUUID().toString().replace("_",""));
        }
        Role role1 = roleService.saveRole(role);
        JSONObject json = new JSONObject();
        json.element("status",true);
        json.element("data",role1);
        return json;
    }
    @RequestMapping("/getRoleById")
    public JSONObject getRoleById(String roleId){
        Role role = roleService.getRoleById(roleId);
        JSONObject json = new JSONObject();
        JsonConfig jc = new JsonConfig();
        jc.setIgnoreDefaultExcludes(false);
        jc.setExcludes(new String[]{"setUser"});
        if(role!=null){
            json.element("status",true);
            json.element("data",role,jc);
        }else{
            json.element("status",false);
        }
        return json;
    }
    @RequestMapping("/delRoleById")
    public JSONObject delRoleById(String roleId){
        JSONObject jsonObject = new JSONObject();
        Role role = roleService.getRoleById(roleId);
        Set<UserInf> set = role.getSetUser();
        if(set.size()>0){
            jsonObject.element("status",false);
            jsonObject.element("msg","该角色被用户引用，无法进行删除操作");
        }else{
            try{
                roleService.delRoleById(roleId);
                jsonObject.element("status",true);
            }catch (Exception e){
                jsonObject.element("status",true);
            }
        }

        return jsonObject;
    }
}
