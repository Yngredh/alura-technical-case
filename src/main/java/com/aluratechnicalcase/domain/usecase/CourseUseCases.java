package com.aluratechnicalcase.domain.usecase;

import com.aluratechnicalcase.application.dto.CourseCreateDTO;
import com.aluratechnicalcase.domain.entity.Course;
import com.aluratechnicalcase.domain.exception.CourseAlreadyExistException;
import com.aluratechnicalcase.domain.exception.CourseNotFoundException;
import com.aluratechnicalcase.domain.exception.UnssuportedOperationException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseUseCases {
    void createCourse(CourseCreateDTO courseCreateDTO) throws UnssuportedOperationException, CourseAlreadyExistException;
    void inactivateCourse(String code) throws CourseNotFoundException;
    List<Course> findCourses(Boolean isAvailable, int pageNumber, int pageSize);
}