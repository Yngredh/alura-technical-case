package com.aluratechnicalcase.domain.entity;

import com.aluratechnicalcase.application.dto.CourseCreateDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    private String code;
    private String name;
    @ManyToOne
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
