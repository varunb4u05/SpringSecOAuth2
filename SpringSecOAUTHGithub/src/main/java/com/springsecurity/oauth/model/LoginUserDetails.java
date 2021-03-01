package com.springsecurity.oauth.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "login_user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDetails {

	@Column(name = "id")
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "social_login_flag")
	private String socialLoginFlag;
	
	@Column(name = "social_login_name")
	private String socialLoginName;
	
	@Column(name = "created_date")
	private Timestamp createdDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialLoginFlag() {
		return socialLoginFlag;
	}

	public void setSocialLoginFlag(String socialLoginFlag) {
		this.socialLoginFlag = socialLoginFlag;
	}

	public String getSocialLoginName() {
		return socialLoginName;
	}

	public void setSocialLoginName(String socialLoginName) {
		this.socialLoginName = socialLoginName;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
