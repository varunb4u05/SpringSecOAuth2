package com.springsecurity.oauth.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {
	
	@GetMapping("/")
	public String helloWorld(){
		return "You don't need to be logged in.";
	}
	
	@GetMapping("/not-restricted")
	public String notRestricted() {
		return "You don't need to be logged in.";
	}

	
	@GetMapping("/restricted")
	public String restricted() {
		return "You are logged in.";
	}

	/*
	 * @GetMapping("/login") public String main(OAuth2AuthenticationToken token) {
	 * System.out.println(token.getPrincipal()); return "secure.html"; }
	 */
}
