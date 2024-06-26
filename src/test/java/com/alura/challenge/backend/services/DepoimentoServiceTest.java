package com.alura.challenge.backend.services;

import com.alura.challenge.backend.domain.dtos.DepoimentoDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoInsertDto;
import com.alura.challenge.backend.domain.entities.Depoimento;
import com.alura.challenge.backend.repositories.DepoimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

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
