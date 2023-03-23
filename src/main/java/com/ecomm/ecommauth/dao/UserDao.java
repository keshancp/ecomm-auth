package com.ecomm.ecommauth.dao;

import com.ecomm.ecommauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
