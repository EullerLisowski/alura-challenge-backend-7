package com.alura.challenge.backend.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.validation.FieldError;

class StandardErrorTest {

    @Test
    void givenNoArgsConstructor_whenCalled_thenObjectCreated() {

        StandardError error = new StandardError();

        assertNull(error.getMessage());
        assertNull(error.getPath());
        assertNull(error.getStatus());
        assertNull(error.getTimeStamp());
    }

    @Test
    void givenAllArgsConstructor_whenCalled_thenObjectCreated() {
        final Long TIME_STAMP = System.currentTimeMillis();
        final Integer STATUS = 404;
        final FieldError FIELD_ERROR = new FieldError("objectName", "field", "message");
        final DataValidationError DATA_VALIDATION_ERROR = new DataValidationError(FIELD_ERROR);
        final List<DataValidationError> ERRORS = List.of(DATA_VALIDATION_ERROR);
        final String MESSAGE = "Test message";
        final String PATH = "/test-uri";

        StandardError error = new StandardError(TIME_STAMP, STATUS, ERRORS, MESSAGE, PATH);

        assertEquals(TIME_STAMP, error.getTimeStamp());
        assertEquals(STATUS, error.getStatus());
        assertEquals(MESSAGE, error.getMessage());
        assertEquals(PATH, error.getPath());
        assertEquals(ERRORS, error.getErrors());
    }
}