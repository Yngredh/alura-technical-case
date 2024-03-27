package com.aluratechnicalcase.infrastructure;

import com.aluratechnicalcase.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Este usuário já existe");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }

    @ExceptionHandler(UnssuportedOperationException.class)
    public ResponseEntity<String> handleUnssuportedOperationException(UnssuportedOperationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Essa ação não pode ser realizada");
    }

    @ExceptionHandler(CourseAlreadyExistException.class)
    public ResponseEntity<String> handleCourseAlreadyExistException(CourseAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("O código do curso já existe");
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
    }
}
