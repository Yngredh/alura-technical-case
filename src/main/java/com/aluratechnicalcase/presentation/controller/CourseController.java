package com.aluratechnicalcase.presentation.controller;

import com.aluratechnicalcase.application.dto.CourseCreateDTO;
import com.aluratechnicalcase.application.service.CourseService;
import com.aluratechnicalcase.domain.exception.CourseAlreadyExistException;
import com.aluratechnicalcase.domain.exception.UnssuportedOperationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody @Valid CourseCreateDTO courseCreateDTO)
            throws UnssuportedOperationException, CourseAlreadyExistException {
        this.courseService.createCourse(courseCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
