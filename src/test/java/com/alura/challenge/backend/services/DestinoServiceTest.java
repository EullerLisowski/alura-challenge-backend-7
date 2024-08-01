package com.alura.challenge.backend.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.alura.challenge.backend.domain.dtos.DestinoDto;
import com.alura.challenge.backend.domain.dtos.DestinoInsertDto;
import com.alura.challenge.backend.domain.entities.Destino;
import com.alura.challenge.backend.repositories.DestinoRepository;

class DestinoServiceTest {
    @InjectMocks
    private DestinoService destinoService;

    @Mock
    private DestinoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Dado destino válido quando destinoService.create então um destinoDto deve ser retornado")
    void testDestinoServiceCreate() {
        // arrange
        final Long ID = 2000L;
        final String NOME = "Nome Teste";
        final String FOTO = "Foto Teste";
        final Double PRECO = 100.00;

        Destino destino = new Destino(ID, NOME, FOTO, PRECO);

        DestinoInsertDto destinoInsertDto = new DestinoInsertDto(NOME, FOTO, PRECO);
        Mockito.when(repository.save(any())).thenReturn(destino);

        // act
        DestinoDto destinoDto = destinoService.create(destinoInsertDto);

        // assert
        assertNotNull(destinoDto);
    }
}
