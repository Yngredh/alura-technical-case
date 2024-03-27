package com.aluratechnicalcase.application.repository;

import com.aluratechnicalcase.domain.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    Boolean existsByCode(String code);
    Page<Course> findAllByIsAvailable(Boolean isAvailable, Pageable pageable);
}
