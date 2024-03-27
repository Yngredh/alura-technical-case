package com.aluratechnicalcase.domain.usecase;

import com.aluratechnicalcase.domain.exception.UnssuportedOperationException;

public interface RegisterUseCases {
    void createRegister(String email, String code) throws UnssuportedOperationException;
}
