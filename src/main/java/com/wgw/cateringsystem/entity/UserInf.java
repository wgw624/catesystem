package com.wgw.cateringsystem.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Date: 2018/9/26 15:55
 *
 * @Author: weiguangwei
 */
@Entity
@Table(name="USER_INF")
public class UserInf  implements Serializable {
    @Id
    @Column(name="user_id")
    private String id;

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
    private String[] rIds;


    public String[] getrIds() {
        return rIds;
    }

    public void setrIds(String[] rIds) {
        this.rIds = rIds;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_role",joinColumns =@JoinColumn(name="u_id",referencedColumnName = "user_id"),inverseJoinColumns =@JoinColumn(name="r_id",referencedColumnName = "role_id"))
    private Set<Role> setRole ;


    public Set<Role> getSetRole() {
        return setRole;
    }

    public void setSetRole(Set<Role> setRole) {
        this.setRole = setRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
