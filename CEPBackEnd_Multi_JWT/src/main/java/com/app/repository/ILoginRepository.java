package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Login;

public interface ILoginRepository extends JpaRepository<Login,String> {

	
	Optional<Login> findByUsername(String username);
}
