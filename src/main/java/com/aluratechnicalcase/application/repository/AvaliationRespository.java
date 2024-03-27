package com.aluratechnicalcase.application.repository;

import com.aluratechnicalcase.domain.entity.Avaliation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliationRespository extends JpaRepository<Avaliation, String> {
    Avaliation findByApprenticeIdAndCourseId(String apprenticeId, String courseId);
}
