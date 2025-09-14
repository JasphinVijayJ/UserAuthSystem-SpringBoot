package com.jasphin.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jasphin.uas.dto.LoginRequest;
import com.jasphin.uas.dto.LoginResponse;
import com.jasphin.uas.model.User;
import com.jasphin.uas.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {
		"http://localhost:5173",
		"https://jasphinvijayj.github.io"
})
@RestController
@RequestMapping("/uas/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public String registerUser(@Valid @RequestBody User user) {
		return userService.registerUser(user);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword()));
	}
}
