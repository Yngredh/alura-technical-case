package com.aluratechnicalcase.domain.entity;

import com.aluratechnicalcase.application.dto.AvaliationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "avaliation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Avaliation {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne
    @JoinColumn(name = "apprentice_id")
    private User apprentice;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    private Integer value;
    private String message;
    private LocalDate avaliationDate;

    public Avaliation(AvaliationDTO avaliationDTO, Course course, User apprentice) {
        this.course = course;
        this.apprentice = apprentice;
        this.value = avaliationDTO.value();
        this.message = avaliationDTO.message();
        this.avaliationDate = LocalDate.now();
    }

}
