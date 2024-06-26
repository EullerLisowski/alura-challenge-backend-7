package com.alura.challenge.backend.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        StandardError standardError = new StandardError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var erros = ex.getFieldErrors();
        StandardError standardError = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                "Requisição inválida.",
                request.getRequestURI());
        standardError.setErrors(erros.stream().map(DataValidationError::new).toList());

        return ResponseEntity.badRequest().body(standardError);
    }

    @ExceptionHandler({NoSuchElementException.class, NoResourceFoundException.class})
    public ResponseEntity<StandardError> noRegisterFound(Exception ex, HttpServletRequest request) {
        StandardError standardError = new StandardError(
                HttpStatus.NOT_FOUND.value(),
                "Nenhum registro encontrado para os parâmetros informados.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(standardError);
    }
}
