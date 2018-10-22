package com.wgw.cateringsystem.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ROLE")
public class Role {
    @Id
    @Column(name="Role_Id")
    private String roleId;

    @Column(name="name")
    private String name;

    @Column(name="describe_Inf",length = 1024)
    private String describe;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "roleInf")
    private Set<UserInf> setUser = new HashSet<UserInf>();

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Set<UserInf> getSetUser() {
        return setUser;
    }

    public void setSetUser(Set<UserInf> setUser) {
        this.setUser = setUser;
    }
}
