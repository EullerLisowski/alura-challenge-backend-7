package com.alura.challenge.backend.exception;

import org.springframework.validation.FieldError;

public record DataValidationError(String field, String message) {
    public DataValidationError(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
