package com.wgw.cateringsystem.service.impl;

import com.wgw.cateringsystem.entity.UserInf;
import com.wgw.cateringsystem.respository.UserInfRespository;
import com.wgw.cateringsystem.service.UserInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
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

    @Override
    public List<UserInf> getUserByParam(UserInf userInf) {

       List<UserInf> userInfList =  userInfRespository.findAll(new Specification<UserInf>() {
            @Override
            public Predicate toPredicate(Root<UserInf> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<String> userName = root.get("userName");
                Path<String> password = root.get("password");
                List<Predicate> predicate = new ArrayList<Predicate>();
                if(userInf.getUserName() !=null && !"".equals(userInf.getUserName())){
                    predicate.add(criteriaBuilder.equal(userName.as(String.class),userInf.getUserName()));

                }
                if(userInf.getPassword()!=null && !"".equals(userInf.getPassword())){
                    predicate.add(criteriaBuilder.equal(password.as(String.class),userInf.getPassword()));
                }
                Predicate []pre = new Predicate[predicate.size()];
                query.where(predicate.toArray(pre));
                return criteriaBuilder.and(predicate.toArray(pre));

            }
        });
        return userInfList;
    }

    @Override
    public UserInf saveUserInf(UserInf userInf) {
        UserInf usr  = userInfRespository.save(userInf);
        return usr;
    }

    @Override
    public boolean delUser(String userId) {
        boolean flag =true;
        try{
            userInfRespository.deleteById(userId);
        }catch(Exception e){
            flag = false;
        }

        return flag;
    }

    @Override
    public UserInf saveOrUpdate(UserInf userInf) {
        UserInf user = userInfRespository.save(userInf);
        return user;
    }
}
