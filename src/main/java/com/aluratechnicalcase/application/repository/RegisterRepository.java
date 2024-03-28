package com.aluratechnicalcase.application.repository;

import com.aluratechnicalcase.domain.entity.Course;
import com.aluratechnicalcase.domain.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, String> {

    Optional<Register> findByUserIdAndCourseId(String userId, String courseId);

    @Query("SELECT r.course FROM Register r GROUP BY r.course HAVING COUNT(r) >= 4")
    List<Course> findAllCoursesWithAtLeastFourUsers();
}
