package com.springsecurity.oauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.oauth.model.LoginUserDetails;

@Repository
public interface LoginUserDetailsRepository extends JpaRepository<LoginUserDetails, Integer>{

	LoginUserDetails findByEmail(String email);
}
