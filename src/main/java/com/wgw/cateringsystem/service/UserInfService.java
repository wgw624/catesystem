package com.wgw.cateringsystem.service;

import com.wgw.cateringsystem.entity.UserInf;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Date: 2018/9/25 17:50
 *
 * @Author: weiguangwei
 */
public interface UserInfService {
  UserInf getUserInf(String userId);
  List<UserInf> getAllUserInf();
  List<UserInf> getUserByParam(UserInf userInf);
}
