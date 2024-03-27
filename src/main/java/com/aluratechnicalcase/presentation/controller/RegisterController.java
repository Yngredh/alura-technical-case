package com.aluratechnicalcase.presentation.controller;

import com.aluratechnicalcase.application.service.RegisterService;
import com.aluratechnicalcase.domain.exception.UnssuportedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestParam String email, @RequestParam String code) throws UnssuportedOperationException {
        this.registerService.createRegister(email, code);
        return null;
    }
}
