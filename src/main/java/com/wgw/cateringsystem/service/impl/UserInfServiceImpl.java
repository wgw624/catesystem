package com.wgw.cateringsystem.service.impl;

import com.wgw.cateringsystem.entity.UserInf;
import com.wgw.cateringsystem.respository.UserInfRespository;
import com.wgw.cateringsystem.service.UserInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return userInfRespository.getOne(userId);
    }
}
