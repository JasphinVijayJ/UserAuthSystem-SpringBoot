package com.jasphin.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasphin.uas.dto.LoginResponse;
import com.jasphin.uas.enums.ErrorMessage;
import com.jasphin.uas.enums.SuccessMessage;
import com.jasphin.uas.exception.InvalidPasswordException;
import com.jasphin.uas.exception.UserNotFoundException;
import com.jasphin.uas.model.Admin;
import com.jasphin.uas.repository.AdminRepository;
import com.jasphin.uas.security.JwtUtil;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

//	Since your TransactionServiceImplement class has only one constructor, you can omit @Autowired
	private JwtUtil jwtUtil;

//	Constructor Injection
	public AdminService(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	public LoginResponse loginAdmin(String email, String password) {
		Admin admin = adminRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage() + email));

		if (!admin.getPassword().equals(password)) {
			throw new InvalidPasswordException(ErrorMessage.INVALID_PASSWORD.getMessage());
		}

		String token = jwtUtil.generateToken(email); // generate JWT
		return new LoginResponse(token, admin.getRole(), SuccessMessage.LOGIN_SUCCESS.getMessage());
	}
}
