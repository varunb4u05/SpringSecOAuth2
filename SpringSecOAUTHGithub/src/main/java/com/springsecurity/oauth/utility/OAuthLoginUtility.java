package com.springsecurity.oauth.utility;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.springsecurity.oauth.model.LoginUserDetails;

@Service
public class OAuthLoginUtility {

	public LoginUserDetails fillLoginUserDetailsObject(String email, String name, String socialName) {
		LoginUserDetails user = new LoginUserDetails();
		Timestamp currDate = Timestamp.valueOf(LocalDateTime.now());
		user.setCreatedDate(currDate);
		user.setSocialLoginFlag("Y");
		user.setSocialLoginName(socialName);
		user.setEmail(email);
		user.setName(name);
		user.setPassword("12345");
		return user;
	}
}
