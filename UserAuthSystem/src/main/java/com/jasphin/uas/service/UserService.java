package com.jasphin.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasphin.uas.dto.LoginResponse;
import com.jasphin.uas.enums.ErrorMessage;
import com.jasphin.uas.enums.SuccessMessage;
import com.jasphin.uas.exception.EmailAlreadyExistsException;
import com.jasphin.uas.exception.InvalidPasswordException;
import com.jasphin.uas.exception.PasswordMismatchException;
import com.jasphin.uas.exception.PhoneAlreadyExistsException;
import com.jasphin.uas.exception.UserNotFoundException;
import com.jasphin.uas.model.User;
import com.jasphin.uas.repository.UserRepository;
import com.jasphin.uas.security.JwtUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
//	Since your TransactionServiceImplement class has only one constructor, you can omit @Autowired
	private JwtUtil jwtUtil;
	
//	Constructor Injection
	public UserService(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	public String registerUser(User user) {
		// Check if phone already exists
		if (userRepository.existsByPhone(user.getPhone())) {
			throw new PhoneAlreadyExistsException(ErrorMessage.PHONE_ALREADY_EXISTS.getMessage());
		}
		// Check if email already exists
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyExistsException(ErrorMessage.EMAIL_ALREADY_EXISTS.getMessage());
		}
		// Check if passwords match
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			throw new PasswordMismatchException(ErrorMessage.PASSWORD_MISMATCH.getMessage());
		}
		
		try {
		    userRepository.save(user);
		    return SuccessMessage.REGISTER_SUCCESS.getMessage();
		} catch (Exception e) {
		    return ErrorMessage.REGISTER_FAILED.getMessage();
		}
	}

	public LoginResponse loginUser(String email, String password) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage() + email));

		if (!user.getPassword().equals(password)) {
			throw new InvalidPasswordException(ErrorMessage.INVALID_PASSWORD.getMessage());
		}
		
		String token = jwtUtil.generateToken(email);	// generate JWT
		return new LoginResponse(token, SuccessMessage.LOGIN_SUCCESS.getMessage());
	}
}
