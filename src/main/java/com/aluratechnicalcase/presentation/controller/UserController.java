package com.aluratechnicalcase.presentation.controller;

import com.aluratechnicalcase.application.dto.UserCreateDTO;
import com.aluratechnicalcase.application.dto.UserResponseDTO;
import com.aluratechnicalcase.application.service.UserService;
import com.aluratechnicalcase.domain.entity.User;
import com.aluratechnicalcase.domain.exception.UserAlreadyExistException;
import com.aluratechnicalcase.domain.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody @Valid UserCreateDTO user) throws UserAlreadyExistException {
        this.userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/findByUsername")
    public UserResponseDTO findByUsername(@RequestParam String username) throws UserNotFoundException {
        return this.userService.findUserByUsername(username);
    }

}
