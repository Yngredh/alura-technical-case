package com.aluratechnicalcase.application.service;

import com.aluratechnicalcase.application.repository.CourseRepository;
import com.aluratechnicalcase.application.repository.RegisterRepository;
import com.aluratechnicalcase.application.repository.UserRepository;
import com.aluratechnicalcase.domain.entity.Course;
import com.aluratechnicalcase.domain.entity.Register;
import com.aluratechnicalcase.domain.entity.User;
import com.aluratechnicalcase.domain.exception.UnssuportedOperationException;
import com.aluratechnicalcase.domain.usecase.RegisterUseCases;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RegisterService implements RegisterUseCases {

    private RegisterRepository registerRepository;
    private UserRepository userRepository;
    private CourseService courseService;

    public RegisterService(RegisterRepository registerRepository, CourseRepository courseRepository,
                           UserRepository userRepository, CourseService courseService) {
        this.registerRepository = registerRepository;
        this.userRepository = userRepository;
        this.courseService = courseService;
    }

    public void createRegister(String email, String code) throws UnssuportedOperationException {
        Course course = courseService.ifCourseIsNotAvailableThrowException(code);

        User user = userRepository.findUserByEmail(email);
        Optional<Register> registerAlreadyExist = this.registerRepository.findByUserIdAndCourseId(user.getId(), course.getId());
        if (registerAlreadyExist.isPresent()) throw new UnssuportedOperationException("This register already exist");

        Register newRegister = new Register(user, course, LocalDate.now());
        this.registerRepository.save(newRegister);
    }
}
