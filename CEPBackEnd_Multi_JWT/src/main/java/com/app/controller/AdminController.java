package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ForgotPasswordAdminDto;
import com.app.dto.UpdateAdminDto;
import com.app.entity.Admin;
import com.app.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService adminService;

	public AdminController() {
	}

	@GetMapping
	public List<Admin> getAll() {
		return adminService.getAll();
	}

	@GetMapping("/findbyid/{adminId}")
	public ResponseEntity<?> getDetailsById(@PathVariable long adminId) {
		try {
			return new ResponseEntity<>(adminService.getByAdminId(adminId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

//	@PostMapping("/signup")
//	public Admin addAdmin(@RequestBody SignupAdminDto traAdmin) {
//		Admin newAdmin = mapper.map(traAdmin, Admin.class);
////		newAdmin.setAdminRole("ROLE_ADMIN");
//		return adminService.insertAdmin(newAdmin);
//	}

//	@PostMapping("/signin")
//	public Admin validateAdmin(@RequestBody LoginDto logDtoAdmin) {
//		return adminService.authenticateAdmin(logDtoAdmin);
//	}

	@DeleteMapping("/remove/{adminId}")
	public ApiResponse removeAdmin(@PathVariable Long adminId) {
//		System.out.println("in del emp " + adminId);
		return new ApiResponse(adminService.deleteAdmin(adminId));
	}

	@PutMapping("/update/{adminId}")
	public Admin updateAdmin(@PathVariable Long adminId, @RequestBody UpdateAdminDto detAdmin) {
		return adminService.editAdmin(adminId, detAdmin);
	}

	@PostMapping("/forgotpassword")
	public Admin forgotAdminPassword(@RequestBody ForgotPasswordAdminDto detAdmin) {
		return adminService.getAdminPassword(detAdmin);
	}

}
