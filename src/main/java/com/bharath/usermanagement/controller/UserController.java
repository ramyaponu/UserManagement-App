package com.bharath.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.usermanagement.entity.UserDetail;
import com.bharath.usermanagement.exception.UserManagementServiceException;
import com.bharath.usermanagement.model.LoginDto;
import com.bharath.usermanagement.model.RegisterUserDTO;
import com.bharath.usermanagement.service.interfaces.UserService;
import com.bharath.usermanagement.utility.UserServiceConstants;

@RestController
@CrossOrigin
@Validated
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping(value = "register-user")

	public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO userData)
			throws UserManagementServiceException {
		userService.registerUser(userData);

		return new ResponseEntity<String>(UserServiceConstants.REGISTER_SUCCESS, HttpStatus.CREATED);
	}
	
	
	@PostMapping(value="/user-login")
	public  ResponseEntity<?> Userlogin(@RequestBody  LoginDto logindto) throws Exception {
        
        String loginmessage=userService.userLogin(logindto);
	return ResponseEntity.ok(loginmessage);	
    
}
}