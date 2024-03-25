package com.aluratechnicalcase.presentation;

import com.aluratechnicalcase.application.dto.UserCreateDTO;
import com.aluratechnicalcase.application.service.UserService;
import com.aluratechnicalcase.domain.entity.User;
import com.aluratechnicalcase.domain.exception.UserAlreadyExistException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody @Valid UserCreateDTO user) throws UserAlreadyExistException {
        User newUser = this.userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
