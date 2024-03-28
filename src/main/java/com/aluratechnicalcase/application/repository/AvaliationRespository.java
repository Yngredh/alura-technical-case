package com.aluratechnicalcase.application.repository;

import com.aluratechnicalcase.domain.entity.Avaliation;
import com.aluratechnicalcase.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliationRespository extends JpaRepository<Avaliation, String> {
    Avaliation findByApprenticeIdAndCourseId(String apprenticeId, String courseId);

    List<Avaliation> findAllByCourseIdIn(List<String> courseId);

}
