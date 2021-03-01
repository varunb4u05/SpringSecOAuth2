package com.springsecurity.oauth.controllers;

import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springsecurity.oauth.model.LoginUserDetails;
import com.springsecurity.oauth.service.LoginUserDetailsService;
import com.springsecurity.oauth.utility.OAuthLoginUtility;

@Controller
public class SecureController {

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	
	@Autowired
	private LoginUserDetailsService loginUserDetailsService;
	
	@Autowired
	private OAuthLoginUtility oAuthLoginUtility;

	
	@GetMapping(value = {"/","/login"})
	public String helloWorld(Model model, OAuth2AuthenticationToken authentication){
		//return "You are in / ";
		OAuth2AuthorizedClient client = authorizedClientService.
				loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
		String userInfoEndpointUri = client.getClientRegistration().
				getProviderDetails().getUserInfoEndpoint().getUri();
		if(!StringUtils.isEmpty(userInfoEndpointUri)) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
			HttpEntity entity = new HttpEntity("", headers);
			ResponseEntity <Map>response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
			Map userAttributes = response.getBody();
			model.addAttribute("name", userAttributes.get("name"));
			model.addAttribute("email", userAttributes.get("email"));
			String userEmail = String.valueOf(model.getAttribute("email"));
			Optional<LoginUserDetails> optUser = loginUserDetailsService.findByEmail(userEmail);
			if(!optUser.isPresent()) {
				LoginUserDetails newUser = oAuthLoginUtility.fillLoginUserDetailsObject(userEmail, 
																String.valueOf(model.getAttribute("name")),
																authentication.getAuthorizedClientRegistrationId());
				LoginUserDetails savedUser = loginUserDetailsService.saveLoginUser(newUser);
				
				/*
				 * URI location = ServletUriComponentsBuilder .fromCurrentRequest()
				 * .path("/{id}") .buildAndExpand(savedUser.getId()).toUri();
				 * System.out.println("location:"+location);
				 */
			}else {
				System.out.println("user with email id:" + userEmail + " already exists.");
			}
			
		}
		return "userinfo";
		//return "redirect:/saveUser";

	}		
}
