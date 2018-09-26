package com.wgw.cateringsystem.service;

import com.wgw.cateringsystem.entity.UserInf;
import org.springframework.stereotype.Service;

/**
 * Date: 2018/9/25 17:50
 *
 * @Author: weiguangwei
 */
public interface UserInfService {
  UserInf getUserInf(String userId);
}
