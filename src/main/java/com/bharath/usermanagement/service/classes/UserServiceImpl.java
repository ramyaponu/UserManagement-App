package com.bharath.usermanagement.service.classes;

import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.bharath.usermanagement.entity.UserDetail;
import com.bharath.usermanagement.entity.UserType;
import com.bharath.usermanagement.exception.UserManagementServiceException;
import com.bharath.usermanagement.model.AuthServiceUserDetails;
import com.bharath.usermanagement.model.LoginDto;
import com.bharath.usermanagement.model.RegisterUserDTO;
import com.bharath.usermanagement.repository.UserRepository;
import com.bharath.usermanagement.service.interfaces.UserService;
import com.bharath.usermanagement.utility.UserServiceConstants;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService,UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	@Transactional(rollbackForClassName = "UserManagementServiceException")
	public void registerUser(RegisterUserDTO userdata) throws UserManagementServiceException {
		if (userdata.getEmail() == null&&userdata.getPhoneNumber()==null) 
			throw new UserManagementServiceException(UserServiceConstants.PHONE_OR_EMAIL_MANDATORY);
		
		if (userdata.getEmail() != null) {
			Optional<UserDetail> userDetails = userRepository.findByEmailOrPhoneNumber(userdata.getEmail(), userdata.getPhoneNumber());
			if (userDetails != null)
				throw new UserManagementServiceException(UserServiceConstants.EMAIL_ALREADY_REGISTERED);

		}
		if (userdata.getPhoneNumber() != null) {
			Optional<UserDetail> userDetails = userRepository.findByPhoneNumber(userdata.getPhoneNumber());
			if (userDetails != null)
				throw new UserManagementServiceException(UserServiceConstants.PHONE_NUMBER_ALREADY_REGISTERED);

		}
		saveUser(userdata);
		//sendNotification(userdata.getEmail(),userdata.getPhoneNumber());
	}



	private UserDetail saveUser(RegisterUserDTO userdata) throws UserManagementServiceException {
		UserDetail newUserDetails = new UserDetail();
		newUserDetails.setEmail(userdata.getEmail());
		newUserDetails.setPhoneNumber(userdata.getPhoneNumber());
		newUserDetails.setPassword(userdata.getPassword());
		if(userdata.getPassword()==null)
			throw new UserManagementServiceException(UserServiceConstants.PASSWORD_MANDATORY);
		
		newUserDetails.setRegisteredAt(LocalDateTime.now());
		if(userdata.getUserType()!=null)
		newUserDetails.setUserType(userdata.getUserType());
		newUserDetails.setUserType(UserType.USER);
		return userRepository.save(newUserDetails);
	}

@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
	Optional<UserDetail> userDetails=	userRepository.findByEmailOrPhoneNumber(username,username);
		return userDetails.map(AuthServiceUserDetails::new ).orElseThrow(()->new UsernameNotFoundException(String.format(UserServiceConstants.USER_NOT_FOUND,username)));
	}



@Override
public String userLogin(LoginDto userdata) throws Exception {
	
	if(userdata.getEmail()!=null) {
		
		Optional<UserDetail> userDet = userRepository.findByEmail(userdata.getEmail());
		UserDetail u=userDet.orElseThrow(()->new Exception("not found"));
		
	
	if(userdata.getPhoneNumber()!=null) {
		Optional<UserDetail> userDetails=userRepository.findByPhoneNumber(userdata.getPhoneNumber());
		if(userDetails.isPresent()){
			return  new String("Login success");
			
		}else {
			return new String ("Login failed");
		}
	}
	
	
			
	}
	
	return "login success";
	
	
	
	
	

}


}
