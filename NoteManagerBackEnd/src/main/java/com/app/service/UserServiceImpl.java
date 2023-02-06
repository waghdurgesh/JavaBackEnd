package com.app.service;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.LoginRequestDto;
import com.app.pojos.User;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostConstruct
	public void init(){
		System.out.println("in init "+mapper);
	}

	@Override
	public User addUserDetails(User transientUser) {
		return userRepo.save(transientUser);
	}

	@Override
	public User fetchUserDetails(Long userId) {
		return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid User Id"));
	}

	@Override
	public User authenticateUser(LoginRequestDto dto) {
		return userRepo.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Wrong Credentials"));
	}
}