package com.aluratechnicalcase.application.repository;

import com.aluratechnicalcase.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
    Boolean existsByCode(String code);
}
