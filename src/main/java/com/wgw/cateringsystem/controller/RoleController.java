package com.wgw.cateringsystem.controller;

import com.wgw.cateringsystem.entity.Role;
import com.wgw.cateringsystem.service.RoleService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
