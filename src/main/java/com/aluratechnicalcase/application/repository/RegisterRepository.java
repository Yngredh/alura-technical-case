package com.aluratechnicalcase.application.repository;

import com.aluratechnicalcase.domain.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, String> {

    Optional<Register> findByUserIdAndCourseId(String userId, String courseId);
}
