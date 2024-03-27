package com.aluratechnicalcase.presentation.controller;

import com.aluratechnicalcase.application.dto.CourseCreateDTO;
import com.aluratechnicalcase.application.service.CourseService;
import com.aluratechnicalcase.domain.entity.Course;
import com.aluratechnicalcase.domain.exception.CourseAlreadyExistException;
import com.aluratechnicalcase.domain.exception.CourseNotFoundException;
import com.aluratechnicalcase.domain.exception.UnssuportedOperationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/inactivate")
    public ResponseEntity inactivateCourse(@RequestParam @NotBlank @Pattern(regexp = "^[a-zA-Z\\-]+$") String code)
            throws CourseNotFoundException {
        this.courseService.inactivateCourse(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find")
    public List<Course> findAll(@RequestParam(defaultValue = "true") Boolean isAvailable,
                                @NotNull Integer pageNumber, @NotNull Integer pageSize) {
        return this.courseService.findCourses(isAvailable, pageNumber, pageSize);
    }
}
