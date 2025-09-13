package com.jasphin.uas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jasphin.uas.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);
	boolean existsByPhone(String phone);
	Optional<User> findByEmail(String email);
}
