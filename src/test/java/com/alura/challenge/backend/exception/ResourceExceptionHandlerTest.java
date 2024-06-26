package com.alura.challenge.backend.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("null")
class ResourceExceptionHandlerTest {

    ResourceExceptionHandler resourceExceptionHandler;

    @BeforeEach
    void setUp() {
        resourceExceptionHandler = new ResourceExceptionHandler();
    }

    @Test
    void givenEntityNotFoundException_whenHandleException_thenReturnsNotFoundResponse() {

        String TEST_MESSAGE = "Test message";
        String TEST_URI = "/test-uri";

        // arrange
        EntityNotFoundException entityNotFoundException = new EntityNotFoundException(TEST_MESSAGE);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn(TEST_URI);

        // act
        ResponseEntity<StandardError> response = resourceExceptionHandler
                .entityNotFoundException(entityNotFoundException, httpServletRequest);

        // assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody().getTimeStamp().getClass());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
        assertEquals(TEST_MESSAGE, response.getBody().getMessage());
        assertEquals(TEST_URI, response.getBody().getPath());
    }

    @Test
    void givenMethodArgumentNotValidException_whenHandleException_thenReturnsBadRequestResponse() {
        String MESSAGE_NOME_REQUIRED = "O campo 'nome' deve ser informado.";
        String MESSAGE_DEPOIMENTO_REQUIRED = "O campo 'depoimento' deve ser informado.";
        String MESSAGE_INVALID_REQUEST = "Requisição inválida.";
        String TEST_URI = "/test-uri";
        String OBJECT_NAME = "objectName";
        String DATA_VALIDATION_ERROR_FIELD_NOME = "nome";
        String DATA_VALIDATION_ERROR_FIELD_DEPOIMENTO = "depoimento";

        // arrange
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        List<FieldError> fieldErrors = List.of(
                new FieldError(OBJECT_NAME, DATA_VALIDATION_ERROR_FIELD_NOME, MESSAGE_NOME_REQUIRED),
                new FieldError(OBJECT_NAME, DATA_VALIDATION_ERROR_FIELD_DEPOIMENTO, MESSAGE_DEPOIMENTO_REQUIRED));
        when(methodArgumentNotValidException.getFieldErrors()).thenReturn(fieldErrors);

        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn(TEST_URI);

        // act
        ResponseEntity<StandardError> response = resourceExceptionHandler
                .methodArgumentNotValidException(methodArgumentNotValidException, httpServletRequest);

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertNotNull(response.getBody().getTimeStamp().getClass());
        assertEquals(MESSAGE_INVALID_REQUEST, response.getBody().getMessage());
        assertEquals(TEST_URI, response.getBody().getPath());
        assertEquals(2, response.getBody().getErrors().size());
        assertEquals(DATA_VALIDATION_ERROR_FIELD_NOME, response.getBody().getErrors().get(0).field());
        assertEquals(MESSAGE_NOME_REQUIRED, response.getBody().getErrors().get(0).message());
        assertEquals(DATA_VALIDATION_ERROR_FIELD_DEPOIMENTO, response.getBody().getErrors().get(1).field());
        assertEquals(MESSAGE_DEPOIMENTO_REQUIRED, response.getBody().getErrors().get(1).message());
    }

    @Test
    void givenNoSuchElementException_whenHandleException_thenReturnsNotFoundResponse() {

        String TEST_MESSAGE = "Nenhum registro encontrado para os parâmetros informados.";
        String TEST_URI = "/test-uri";

        // arrange
        NoSuchElementException noSuchElementException = new NoSuchElementException(TEST_MESSAGE);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn(TEST_URI);

        // act
        ResponseEntity<StandardError> response = resourceExceptionHandler
                .noRegisterFound(noSuchElementException, httpServletRequest);

        // assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody().getTimeStamp().getClass());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
        assertEquals(TEST_MESSAGE, response.getBody().getMessage());
        assertEquals(TEST_URI, response.getBody().getPath());
    }
}