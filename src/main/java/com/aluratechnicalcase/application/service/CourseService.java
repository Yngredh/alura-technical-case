package com.aluratechnicalcase.application.service;

import com.aluratechnicalcase.application.dto.CourseCreateDTO;
import com.aluratechnicalcase.application.repository.CourseRepository;
import com.aluratechnicalcase.application.repository.UserRepository;
import com.aluratechnicalcase.domain.entity.Course;
import com.aluratechnicalcase.domain.entity.Role;
import com.aluratechnicalcase.domain.entity.User;
import com.aluratechnicalcase.domain.exception.CourseAlreadyExistException;
import com.aluratechnicalcase.domain.exception.UnssuportedOperationException;
import com.aluratechnicalcase.domain.usecase.CourseUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CourseService implements CourseUseCases {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void createCourse(CourseCreateDTO courseCreateDTO) throws UnssuportedOperationException, CourseAlreadyExistException {
        User user = this.userRepository.findUserByUsername(courseCreateDTO.instructor());
        if (!user.getRole().equals(Role.INSTRUTOR)) throw new UnssuportedOperationException("Non instructor users cannot create courses");

        Boolean courseAlreadyExist = this.courseRepository.existsByCode(courseCreateDTO.code());
        if (courseAlreadyExist) throw new CourseAlreadyExistException("This course code already exist");

        this.courseRepository.save(new Course(courseCreateDTO, user, LocalDate.now()));
    }

}
