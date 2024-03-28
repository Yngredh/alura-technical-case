package com.aluratechnicalcase.application.repository;

import com.aluratechnicalcase.application.dto.UserResponseDTO;
import com.aluratechnicalcase.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Boolean existsByUsernameOrEmail(String username, String email);
    Optional<UserResponseDTO> findByUsername(String username);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}