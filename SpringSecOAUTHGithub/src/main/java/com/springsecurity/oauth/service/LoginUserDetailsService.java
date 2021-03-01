package com.springsecurity.oauth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.oauth.model.LoginUserDetails;
import com.springsecurity.oauth.repository.LoginUserDetailsRepository;

@Service
public class LoginUserDetailsService {

	@Autowired
	private LoginUserDetailsRepository loginUserDetailsRepository;
	
	public LoginUserDetails saveLoginUser(LoginUserDetails user) {
		
		LoginUserDetails savedUser = loginUserDetailsRepository.save(user);
		return savedUser;
	}
	
	public Optional<LoginUserDetails> findByEmail(String userEmail) {
		LoginUserDetails optUser = loginUserDetailsRepository.findByEmail(userEmail);
		Optional<LoginUserDetails> opt = Optional.ofNullable(optUser);
		return opt;
	}
}
