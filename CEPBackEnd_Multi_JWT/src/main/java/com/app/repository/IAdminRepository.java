package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.*;

public interface IAdminRepository extends JpaRepository<Admin, Long>  {
	Optional<Admin> findByAdminEmailAndAdminPassword(String email, String pass);

	Optional<Admin> findByAdminEmailAndAdminFirstnameAndAdminLastname(String email, String firstName, String lastName);
	
	Optional<Admin> findByLogin(Login login);

}
