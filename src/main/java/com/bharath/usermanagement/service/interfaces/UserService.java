package com.bharath.usermanagement.service.interfaces;

//import org.springframework.security.core.userdetails.UserDetails;

import com.bharath.usermanagement.entity.UserDetail;
import com.bharath.usermanagement.exception.UserManagementServiceException;
import com.bharath.usermanagement.model.LoginDto;
import com.bharath.usermanagement.model.RegisterUserDTO;

public interface UserService {
public void registerUser(RegisterUserDTO userdata) throws UserManagementServiceException;

public String userLogin(LoginDto logindto) throws Exception;















}
