package com.ecom.productcatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.productcatalog.dto.response.UserDto;
import com.ecom.productcatalog.mapper.UserMapper;
import com.ecom.productcatalog.model.Users;
import com.ecom.productcatalog.service.UserService;

@RestController
public class UsersController {

	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/register")
	public UserDto registerUser(@RequestBody Users user) {
		Users saved = userService.registerUser(user);
	    UserDto dto = UserMapper.toDto(saved);
		return dto;
	}
	
	
	@PostMapping("/login")
	public String loginUser(@RequestBody Users user) {
		
		return userService.verify(user);
	}
	
}

