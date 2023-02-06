package com.app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginRequestDto;
import com.app.dto.UserSignupDto;
import com.app.pojos.User;
import com.app.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/signup")
	public User saveUserDetails(@RequestBody UserSignupDto user){
		User newUser = mapper.map(user, User.class);
		return userService.addUserDetails(newUser);	
	}
	
	@GetMapping("/{userId}")
	public User getUserDetails(@PathVariable Long userId){
		System.out.println("in get emp details "+userId);
		return userService.fetchUserDetails(userId);
	}
	
	@PostMapping("/signin")
	public User validateUser(@RequestBody LoginRequestDto dto){
		System.out.println("in emp signin "+dto);
		return userService.authenticateUser(dto);
	}
}