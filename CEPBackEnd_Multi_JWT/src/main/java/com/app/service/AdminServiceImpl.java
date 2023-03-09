package com.app.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.ForgotPasswordAdminDto;
import com.app.dto.LogedInUserDto;
import com.app.dto.UpdateAdminDto;
import com.app.entity.Admin;
import com.app.entity.Login;
import com.app.entity.Student;
import com.app.repository.IAdminRepository;
import com.app.repository.ILoginRepository;
import com.app.repository.IStudentRepository;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private IAdminRepository adminRepo;

	@Autowired
	private ILoginRepository loginrepo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private IStudentRepository studentRepository;

	@Override
	public List<Admin> getAll() {
		return adminRepo.findAll();
	}

	@Override
	public Admin getByAdminId(Long id) {
		return adminRepo.findById(id).get();
	}

//	@Override
//	public Admin insertAdmin(Admin traAdmin) {
//		return adminRepo.save(traAdmin);
//	}

	@Override
	public Login insertAdmin(Admin traAdmin) {
		System.out.println("traadmin" + traAdmin);
		Login login = new Login();
		login.setUsername(traAdmin.getAdminEmail());
		login.setPassword(encoder.encode(traAdmin.getAdminPassword()));
		insertLogin(login);
		System.out.println("login" + login);
		Login login2 = loginrepo.findById(traAdmin.getAdminEmail()).get();
		login2.addAdmin(traAdmin);
		return loginrepo.save(login2);
	}

	public void insertLogin(Login login) {
		loginrepo.save(login);
	}

	@Override
	public String deleteAdmin(Long id) {
		if (adminRepo.existsById(id)) {
			adminRepo.deleteById(id);
			return "Admin Succesfully Deleted";
		}
		return "Admin Deletion Failed : Invalid Id";
	}

	@Override
	public Admin editAdmin(Long id, UpdateAdminDto detachedAdmin) {
		if (adminRepo.existsById(id)) {
			Admin upAdmin = getByAdminId(id);
			upAdmin.setAdminEmail(detachedAdmin.getAdminEmail());
			upAdmin.setAdminFirstname(detachedAdmin.getAdminFirstname());
			upAdmin.setAdminLastname(detachedAdmin.getAdminLastname());
			return adminRepo.save(upAdmin);
		}
		throw new ResourceNotFoundException("Invalid Admin Id : Updation Failed");
	}

//	@Override
//	public Admin authenticateAdmin(LoginDto logDtoAdmin) {
//		return adminRepo.findByAdminEmailAndAdminPassword(logDtoAdmin.getEmail(), logDtoAdmin.getPassword())
//				.orElseThrow(() -> new ResourceNotFoundException("Invalid Admin Credentials !"));
//	}

	@Override
	public Admin getAdminPassword(ForgotPasswordAdminDto adminDetails) {
		return adminRepo.findByAdminEmailAndAdminFirstnameAndAdminLastname(adminDetails.getEmail(), adminDetails.getFirstname(),
						adminDetails.getLastname()).orElseThrow(() -> new ResourceNotFoundException("Invalid Admin Credentials ! Admin Not Exist"));
	}

	@Override
	public LogedInUserDto getByUsername(String username) {
		Login login = loginrepo.findById(username).orElseThrow(() -> new RuntimeException("user not found"));
		try {
			Admin admin = adminRepo.findByLogin(login).get();
			LogedInUserDto logedInUserDto = new LogedInUserDto();
			logedInUserDto.setUserEmail(admin.getAdminEmail());
			logedInUserDto.setUserFirstname(admin.getAdminFirstname());
			logedInUserDto.setUserLastname(admin.getAdminLastname());
			logedInUserDto.setUserId(admin.getAdminId());
			logedInUserDto.setUsername(username);
			logedInUserDto.setUserrole("ROLE_ADMIN");
			return logedInUserDto;
		} catch (NoSuchElementException e) {
			System.out.println("checking in student table");
		}
		Student student = studentRepository.findByLogin(login).get();
		LogedInUserDto inUserDto = new LogedInUserDto();
		inUserDto.setUserEmail(student.getStdEmail());
		inUserDto.setUserFirstname(student.getStdFirstname());
		inUserDto.setUserLastname(student.getStdLastname());
		inUserDto.setUserId(student.getStdPrn());
		inUserDto.setUsername(username);
		inUserDto.setUserrole("ROLE_STUDENT");
		return inUserDto;
	}

}
