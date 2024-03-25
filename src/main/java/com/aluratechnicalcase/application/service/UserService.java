package com.aluratechnicalcase.application.service;

import com.aluratechnicalcase.application.dto.UserCreateDTO;
import com.aluratechnicalcase.application.repository.UserRepository;
import com.aluratechnicalcase.domain.entity.User;
import com.aluratechnicalcase.domain.exception.UserAlreadyExistException;
import com.aluratechnicalcase.domain.usecase.UserUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService implements UserUseCases {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserCreateDTO userCreateDTO) throws UserAlreadyExistException {
        Boolean userAlreadyExist = this.userRepository.existsByUsernameOrEmail(userCreateDTO.username(), userCreateDTO.email());
        if (userAlreadyExist) throw new UserAlreadyExistException("Couldn't create new user because this one already exist");

        LocalDate creationDate = LocalDate.now();
        User newUser = new User(userCreateDTO, creationDate);
        this.userRepository.save(newUser);
        return newUser;
    }

}
