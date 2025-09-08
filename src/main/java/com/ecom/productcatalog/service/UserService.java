package com.ecom.productcatalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.productcatalog.model.Users;
import com.ecom.productcatalog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
		
	
	public Users registerUser(Users user) {
		 String encodedPassword = passwordEncoder.encode(user.getPassword());
	     user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	
	public String verify(Users user) {
		Authentication authentication= authManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateTokens(user.getUsername());
		}
		
		return "fail";
	}
	
}
