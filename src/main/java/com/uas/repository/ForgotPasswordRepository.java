package com.uas.repository;

import com.uas.model.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

    void deleteByEmail(String email);

    Optional<ForgotPassword> findByEmail(String email);

    Optional<ForgotPassword> findByEmailAndOtp(String email, String otp);
}