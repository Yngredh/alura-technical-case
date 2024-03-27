package com.aluratechnicalcase.domain.entity;

import com.aluratechnicalcase.application.dto.UserCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Course> courses;

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