package com.app.service;

import com.app.dto.LoginRequestDto;
import com.app.pojos.User;

public interface UserService {
	User addUserDetails(User transientUser);
	User fetchUserDetails(Long userId);
	User authenticateUser(LoginRequestDto dto);
}
