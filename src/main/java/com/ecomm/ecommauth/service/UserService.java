package com.ecomm.ecommauth.service;

import com.ecomm.ecommauth.entity.User;
import com.ecomm.ecommauth.exception.AuthServiceException;

public interface UserService {

    User getUser(String username) throws AuthServiceException;
}
