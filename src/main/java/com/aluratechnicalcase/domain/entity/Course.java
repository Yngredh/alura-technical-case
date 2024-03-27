package com.aluratechnicalcase.domain.entity;

import com.aluratechnicalcase.application.dto.CourseCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @UuidGenerator
    private String id;
    @Column(unique = true)
    private String code;
    private String name;
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;
    private String description;
    private Boolean isAvailable;
    private LocalDate inactivationDate;
    private LocalDate creationDate;

    public Course(CourseCreateDTO courseCreateDTO, User instructor, LocalDate creationDate){
        this.name = courseCreateDTO.name();
        this.code = courseCreateDTO.code();
        this.instructor = instructor;
        this.description = courseCreateDTO.description();
        this.isAvailable = true;
        this.creationDate = creationDate;
    }
}
