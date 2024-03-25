package com.aluratechnicalcase.application.repository;

import com.aluratechnicalcase.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsernameOrEmail(String username, String email);
}