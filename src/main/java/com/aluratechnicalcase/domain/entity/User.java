package com.aluratechnicalcase.domain.entity;

import com.aluratechnicalcase.application.dto.UserCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDate creationDate;

    public User(UserCreateDTO user, LocalDate creationDate) {
        this.name = user.name();
        this.username = user.username();
        this.email = user.email();
        this.password = user.password();
        this.role = user.role();
        this.creationDate = creationDate;
    }
}
