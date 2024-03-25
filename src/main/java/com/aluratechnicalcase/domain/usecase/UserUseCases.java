package com.aluratechnicalcase.domain.usecase;

import com.aluratechnicalcase.application.dto.UserCreateDTO;
import com.aluratechnicalcase.application.dto.UserResponseDTO;
import com.aluratechnicalcase.domain.entity.User;
import com.aluratechnicalcase.domain.exception.UserAlreadyExistException;
import com.aluratechnicalcase.domain.exception.UserNotFoundException;

public interface UserUseCases {
    User createUser(UserCreateDTO userCreateDTO) throws UserAlreadyExistException;

    UserResponseDTO findUserByUsername(String username) throws UserNotFoundException;
}
