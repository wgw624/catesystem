package com.wgw.cateringsystem.respository;

import com.wgw.cateringsystem.entity.UserInf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Date: 2018/9/26 16:33
 *
 * @Author: weiguangwei
 */

@Repository
public interface UserInfRespository extends JpaRepository<UserInf,String>,JpaSpecificationExecutor<UserInf>{
}
