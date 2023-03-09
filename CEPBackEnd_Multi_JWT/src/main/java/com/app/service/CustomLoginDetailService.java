package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Login;
import com.app.repository.ILoginRepository;

@Service
@Transactional
public class CustomLoginDetailService implements UserDetailsService {

	@Autowired
	private ILoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("in load by user nm " + username);

		Login login = loginRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("invalid username"));
		System.out.println("lifted user dtls from db " + login);
		return new CustomLoginDetails(login);
	}

}
