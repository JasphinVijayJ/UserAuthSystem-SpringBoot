package com.uas.service;

import com.uas.dto.LoginResponse;
import com.uas.enums.ErrorMessage;
import com.uas.enums.SuccessMessage;
import com.uas.exception.InvalidPasswordException;
import com.uas.exception.UserNotFoundException;
import com.uas.model.Admin;
import com.uas.repository.AdminRepository;
import com.uas.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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