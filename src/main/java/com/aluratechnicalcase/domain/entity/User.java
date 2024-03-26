package com.aluratechnicalcase.domain.entity;

import com.aluratechnicalcase.application.dto.UserCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@IdClass(UserId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private String username;
    @Id
    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate creationDate;

    public User(UserCreateDTO user, LocalDate creationDate) {
        this.username = user.username();
        this.email = user.email();
        this.name = user.name();
        this.password = user.password();
        this.role = user.role();
        this.creationDate = creationDate;
    }
}
