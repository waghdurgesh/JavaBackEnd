package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.entity.Admin;
import com.app.repository.IStudentRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private IStudentRepository studentRepository;

	@Autowired
	private IAdminService adminService;

	public void sendEmailsToStudents() {
		List<Admin> admins = adminService.getAll();
		for (Admin admin : admins) {
			String email = admin.getAdminEmail();
			String password = admin.getAdminPassword();
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setSubject("Your login credentials");
			message.setText(
					"Dear " + admin.getAdminFirstname() + ",\n\n" + "Your email: " + email + "\n" + "Your password: "
							+ password + "\n\n" + "Please use these credentials to log in to our platform.\n\n"
							+ "Best regards,\n" + "Your School");
			javaMailSender.send(message);
		}
	}
//        List<Student> students = studentRepository.findAll();
//        for (Student student : students) {
//            String email = student.getStdEmail();
//            String password = student.getStdPass();
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(email);
//            message.setSubject("Your login credentials");
//            message.setText("Dear " + student.getStdFirstname() + ",\n\n"
//                    + "Your email: " + email + "\n"
//                    + "Your password: " + password + "\n\n"
//                    + "Please use these credentials to log in to our platform.\n\n"
//                    + "Best regards,\n"
//                    + "Your School");
//            javaMailSender.send(message);
//        }
//    }
}
