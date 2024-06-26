package com.alura.challenge.backend.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = StandardError.class)
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
        Long TIME_STAMP = System.currentTimeMillis();
        Integer STATUS = 404;
        FieldError FIELD_ERROR = new FieldError("objectName", "field", "message");
        DataValidationError DATA_VALIDATION_ERROR = new DataValidationError(FIELD_ERROR);
        List<DataValidationError> ERRORS = List.of(DATA_VALIDATION_ERROR);
        String MESSAGE = "Test message";
        String PATH = "/test-uri";

        StandardError error = new StandardError(TIME_STAMP, STATUS, ERRORS, MESSAGE, PATH);

        assertEquals(TIME_STAMP, error.getTimeStamp());
        assertEquals(STATUS, error.getStatus());
        assertEquals(MESSAGE, error.getMessage());
        assertEquals(PATH, error.getPath());
        assertEquals(ERRORS, error.getErrors());
    }
}