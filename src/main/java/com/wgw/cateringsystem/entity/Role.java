package com.wgw.cateringsystem.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ROLE")
public class Role implements Serializable{
    @Id
    @Column(name="role_id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="describe_Inf",length = 1024)
    private String describe;

    @ManyToMany(mappedBy = "setRole")
    private Set<UserInf> setUser = new HashSet<UserInf>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
