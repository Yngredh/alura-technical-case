package com.aluratechnicalcase.domain.usecase;

import com.aluratechnicalcase.application.dto.UserCreateDTO;
import com.aluratechnicalcase.domain.entity.User;
import com.aluratechnicalcase.domain.exception.UserAlreadyExistException;

public interface UserUseCases {
    User createUser(UserCreateDTO userCreateDTO) throws UserAlreadyExistException;
}
