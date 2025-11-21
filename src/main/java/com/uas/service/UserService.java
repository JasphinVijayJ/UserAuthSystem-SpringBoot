package com.uas.service;

import com.uas.dto.LoginResponse;
import com.uas.enums.ErrorMessage;
import com.uas.enums.SuccessMessage;
import com.uas.exception.*;
import com.uas.model.User;
import com.uas.repository.UserRepository;
import com.uas.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        String token = jwtUtil.generateToken(email); // generate JWT
        return new LoginResponse(token, user.getRole(), SuccessMessage.LOGIN_SUCCESS.getMessage());
    }
}