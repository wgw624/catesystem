package com.wgw.cateringsystem.service.impl;

import com.wgw.cateringsystem.entity.Role;
import com.wgw.cateringsystem.respository.RoleRespository;
import com.wgw.cateringsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Date: 2018/10/23 16:19
 *
 * @Author: weiguangwei
 */

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    RoleRespository roleRespository;
    @Override
    public List<Role> getAllRole() {
        return roleRespository.findAll();
    }
}
