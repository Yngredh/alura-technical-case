package com.aluratechnicalcase.application.service;

import com.aluratechnicalcase.application.dto.UserCreateDTO;
import com.aluratechnicalcase.application.dto.UserResponseDTO;
import com.aluratechnicalcase.application.repository.UserRepository;
import com.aluratechnicalcase.domain.entity.User;
import com.aluratechnicalcase.domain.exception.UserAlreadyExistException;
import com.aluratechnicalcase.domain.exception.UserNotFoundException;
import com.aluratechnicalcase.domain.usecase.UserUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService implements UserUseCases {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserCreateDTO userCreateDTO) throws UserAlreadyExistException {
        Boolean userAlreadyExist = this.userRepository.existsByUsernameOrEmail(userCreateDTO.username(), userCreateDTO.email());
        if (userAlreadyExist) throw new UserAlreadyExistException("Couldn't create new user because this one already exist");

        this.userRepository.save(new User(userCreateDTO, LocalDate.now()));
    }

    @Override
    public UserResponseDTO findUserByUsername(String username) throws UserNotFoundException {
        Optional<UserResponseDTO> response = this.userRepository.findByUsername(username);
        if (response.isEmpty()) throw new UserNotFoundException(String.format("Username [%s] were not found", username));
        else return response.get();
    }
}
