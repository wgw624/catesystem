package com.wgw.cateringsystem.service;

import com.wgw.cateringsystem.entity.Role;

import java.util.List;

/**
 * Date: 2018/10/23 16:19
 *
 * @Author: weiguangwei
 */
public interface RoleService {
    List<Role> getAllRole();
    Role getRoleById(String roleId);
    Role saveRole(Role role);
}
