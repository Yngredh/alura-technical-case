package com.aluratechnicalcase.domain.usecase;

import com.aluratechnicalcase.application.dto.CourseCreateDTO;
import com.aluratechnicalcase.domain.exception.CourseAlreadyExistException;
import com.aluratechnicalcase.domain.exception.CourseNotFoundException;
import com.aluratechnicalcase.domain.exception.UnssuportedOperationException;

public interface CourseUseCases {
    void createCourse(CourseCreateDTO courseCreateDTO) throws UnssuportedOperationException, CourseAlreadyExistException;
    void inactivateCourse(String code) throws CourseNotFoundException;
}