package com.wgw.cateringsystem.service.impl;

import com.wgw.cateringsystem.entity.UserInf;
import com.wgw.cateringsystem.respository.UserInfRespository;
import com.wgw.cateringsystem.service.UserInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Date: 2018/9/26 16:39
 *
 * @Author: weiguangwei
 */
@Service
public class UserInfServiceImpl implements UserInfService{

    @Autowired
    private UserInfRespository userInfRespository;

    @Override
    public UserInf getUserInf(String userId) {
        Optional<UserInf> userInf = userInfRespository.findById(userId);
        return userInf.get();
    }
    @Override
    public List<UserInf> getAllUserInf() {
        List<UserInf> userInfList = userInfRespository.findAll();
        return userInfList;
    }
}
