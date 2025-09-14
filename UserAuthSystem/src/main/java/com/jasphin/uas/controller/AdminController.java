package com.jasphin.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasphin.uas.dto.LoginRequest;
import com.jasphin.uas.dto.LoginResponse;
import com.jasphin.uas.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/uas/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(adminService.loginAdmin(loginRequest.getEmail(), loginRequest.getPassword()));
	}
}
