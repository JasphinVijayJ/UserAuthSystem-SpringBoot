package com.jasphin.uas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jasphin.uas.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByEmail(String email);
}
