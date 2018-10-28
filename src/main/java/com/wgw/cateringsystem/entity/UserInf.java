package com.wgw.cateringsystem.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Date: 2018/9/26 15:55
 *
 * @Author: weiguangwei
 */
@Entity
@Table(name="USER_INF")
public class UserInf {
    @Id
    private String sysId;

    @Column
    private String userNo;
    @Column
    private String userName;
    @Column
    private String showName;
    @Column
    private String password;
    @Column
    private String sex;
    @Column
    private String phone;

    @Transient
    private String[] roleId;

    public String[] getRoleId() {
        return roleId;
    }

    public void setRoleId(String[] roleId) {
        this.roleId = roleId;
    }

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "setUser")
    private Set<Role> setRole = new HashSet<Role>();

    public Set<Role> getSetRole() {
        return setRole;
    }

    public void setSetRole(Set<Role> setRole) {
        this.setRole = setRole;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
