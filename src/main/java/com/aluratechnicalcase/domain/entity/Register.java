package com.aluratechnicalcase.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "register")
public class Register {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Course course;
    private LocalDate registrationDate;

    public Register(User user, Course course, LocalDate registrationDate) {
        this.user = user;
        this.course = course;
        this.registrationDate = registrationDate;
    }
}
