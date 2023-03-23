package com.ecomm.ecommauth.dao;

import com.ecomm.ecommauth.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole,Long> {
    List<UserRole> findByUserId(Long id);
}
