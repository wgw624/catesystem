package com.wgw.cateringsystem.respository;

import com.wgw.cateringsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Date: 2018/10/23 16:13
 *
 * @Author: weiguangwei
 */

@Repository
public interface RoleRespository extends JpaRepository<Role,String>,JpaSpecificationExecutor<Role> {
}
