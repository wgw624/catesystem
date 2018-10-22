package com.wgw.cateringsystem.entity;

import javax.persistence.*;

/**
 * Date: 2018/9/26 15:55
 *
 * @Author: weiguangwei
 */
@Entity
@Table(name="USER_INF")
public class UserInf {
    @Id
    @GeneratedValue
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

    @ManyToOne
    @JoinColumn(name="roleId")
    private Role roleInf;

    public Role getRoleInf() {
        return roleInf;
    }

    public void setRoleInf(Role roleInf) {
        this.roleInf = roleInf;
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
