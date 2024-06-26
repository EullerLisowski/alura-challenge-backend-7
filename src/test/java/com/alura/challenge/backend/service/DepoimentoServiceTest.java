package com.alura.challenge.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import com.alura.challenge.backend.domain.entities.Depoimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.alura.challenge.backend.domain.dtos.DepoimentoDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoInsertDto;
import com.alura.challenge.backend.repositories.DepoimentoRepository;
import com.alura.challenge.backend.services.DepoimentoService;

class DepoimentoServiceTest {

    @InjectMocks
    private DepoimentoService depoimentoService;

    @Mock
    private DepoimentoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        // arrange
        Long ID = 2000L;
        String NOME = "Nome Teste";
        String DEPOIMENTO = "Depoimento Teste";
        String FOTO = "Foto Teste";
        Depoimento depoimento = new Depoimento(ID, NOME, DEPOIMENTO, FOTO);

        DepoimentoInsertDto depoimentoInsertDto = new DepoimentoInsertDto(NOME, DEPOIMENTO, FOTO);
        Mockito.when(repository.save(any())).thenReturn(depoimento);

        // act
        DepoimentoDto depoimentoDto = depoimentoService.create(depoimentoInsertDto);

        // assert
        assertNotNull(depoimentoDto);
        assertEquals(NOME, depoimentoDto.nome());
        assertEquals(DEPOIMENTO, depoimentoDto.depoimento());
        assertEquals(FOTO, depoimentoDto.foto());
    }
}
