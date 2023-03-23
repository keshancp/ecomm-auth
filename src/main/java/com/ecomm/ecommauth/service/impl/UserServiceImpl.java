package com.ecomm.ecommauth.service.impl;

import com.ecomm.ecommauth.dao.RoleDao;
import com.ecomm.ecommauth.dao.UserDao;
import com.ecomm.ecommauth.dao.UserRoleDao;
import com.ecomm.ecommauth.entity.Role;
import com.ecomm.ecommauth.entity.User;
import com.ecomm.ecommauth.entity.UserRole;
import com.ecomm.ecommauth.exception.AuthServiceException;
import com.ecomm.ecommauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Role> roleList=null;

       User user= userDao.findByEmail(username);
       List<UserRole> userRoleList =userRoleDao.findByUserId(user.getId());

        if(!userRoleList.isEmpty()){
                List<Long> roleIdList=new ArrayList<>();
                userRoleList.stream().forEach(i-> roleIdList.add(i.getId()));
                roleList= roleDao.findAllById(roleIdList);
        }

       if(user==null){
           throw new UsernameNotFoundException("User not found");
       }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roleList.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole_code()));
        });


        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }

    @Override
    public User getUser(String username) throws AuthServiceException {
        return userDao.findByEmail(username);
    }

}
