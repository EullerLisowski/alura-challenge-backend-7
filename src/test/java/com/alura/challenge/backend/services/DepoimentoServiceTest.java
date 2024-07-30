package com.alura.challenge.backend.services;

import com.alura.challenge.backend.domain.dtos.DepoimentoDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoInsertDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoUpdateDto;
import com.alura.challenge.backend.domain.entities.Depoimento;
import com.alura.challenge.backend.repositories.DepoimentoRepository;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    void givenValidDepoimento_whenServiceCreate_thenAValidDepoimentoDtoShouldBeReturned() {
        // arrange
        final Long ID = 2000L;
        final String NOME = "Nome Teste";
        final String DEPOIMENTO = "Depoimento Teste";
        final String FOTO = "Foto Teste";
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

    @Test
    void givenExistingIdDepoimento_whenServiceFindById_thenTheDepoimentoDtoMatchingIdShouldBeReturned() {
        // arrange
        final Long ID = 2000L;
        final String NOME = "Nome Teste";
        final String DEPOIMENTO = "Depoimento Teste";
        final String FOTO = "Foto Teste";

        Depoimento depoimento = new Depoimento(ID, NOME, DEPOIMENTO, FOTO);
        Mockito.when(repository.findByIdAndAtivoTrue(ID)).thenReturn(java.util.Optional.of(depoimento));

        // act
        DepoimentoDto depoimentoDto = depoimentoService.findById(ID);

        // assert
        assertNotNull(depoimentoDto);
        assertEquals(NOME, depoimentoDto.nome());
        assertEquals(DEPOIMENTO, depoimentoDto.depoimento());
        assertEquals(FOTO, depoimentoDto.foto());
    }

    @Test
    void givenInexistingIdDepoimento_whenServiceFindById_thenExceptionShouldBeThrown() {
        // arrange
        final Long ID = 2000L;
        Mockito.when(repository.findByIdAndAtivoTrue(ID)).thenThrow(NoSuchElementException.class);

        // assert
        assertThrows(NoSuchElementException.class, () -> depoimentoService.findById(ID));
    }

    @Test
    void givenExistingIdDepoimento_whenServiceUpdate_thenAValidDepoimentoDtoShouldBeReturned() {
        // arrange
        final Long ID = 2000L;
        final String NOVO_NOME = "Novo Nome Teste";
        final String NOVO_DEPOIMENTO = "Novo Depoimento Teste";
        final String NOVA_FOTO = "Nova Foto Teste";
        DepoimentoUpdateDto depoimentoUpdateDto = new DepoimentoUpdateDto(ID, NOVO_NOME, NOVO_DEPOIMENTO, NOVA_FOTO);
        Mockito.when(repository.updateDepoimentoInfoById(ID, NOVO_NOME, NOVO_DEPOIMENTO, NOVA_FOTO)).thenReturn(1);

        // act
        DepoimentoDto depoimentoDto = depoimentoService.update(ID, depoimentoUpdateDto);

        // assert
        assertNotNull(depoimentoDto);
        assertEquals(ID, depoimentoDto.id());
        assertEquals(NOVO_NOME, depoimentoDto.nome());
        assertEquals(NOVO_DEPOIMENTO, depoimentoDto.depoimento());
        assertEquals(NOVA_FOTO, depoimentoDto.foto());
    }

    @Test
    void givenInexistingIdDepoimento_whenServiceUpdate_thenExceptionShouldBeThrown() {
        // arrange
        final Long ID = 2000L;
        final String NOVO_NOME = "Novo Nome Teste";
        final String NOVO_DEPOIMENTO = "Novo Depoimento Teste";
        final String NOVA_FOTO = "Nova Foto Teste";
        DepoimentoUpdateDto depoimentoUpdateDto = new DepoimentoUpdateDto(ID, NOVO_NOME, NOVO_DEPOIMENTO, NOVA_FOTO);
        Mockito.when(repository.updateDepoimentoInfoById(ID, NOVO_NOME, NOVO_DEPOIMENTO, NOVA_FOTO)).thenReturn(0);

        // assert
        assertThrows(EntityNotFoundException.class, () -> depoimentoService.update(ID, depoimentoUpdateDto));
    }

    @Test
    void givenExistingIdDepoimento_whenServiceDelete_thenDepoimentoShouldBeDeleted() {
        // arrange
        final Long ID = 2000L;
        Mockito.when(repository.updateDepoimentoDisabled(ID)).thenReturn(1);

        // act
        assertDoesNotThrow(() -> depoimentoService.delete(ID));

        // assert
        Mockito.verify(repository, Mockito.times(1)).updateDepoimentoDisabled(ID);
    }

    @Test
    void givenInexistingIdDepoimento_whenServiceDelete_thenExceptionShouldBeThrown() {
        // arrange
        final Long ID = 2000L;
        Mockito.when(repository.updateDepoimentoDisabled(ID)).thenReturn(0);

        // assert
        assertThrows(EntityNotFoundException.class, () -> depoimentoService.delete(ID));
    }

    @Test
    void findRandomThreeActive_shouldReturnListOfThreeDepoimentoDto() {
        // arrange
        final Long ID1 = 1L;
        final Long ID2 = 2L;
        final Long ID3 = 3L;
        final String NOME1 = "Nome 1";
        final String NOME2 = "Nome 2";
        final String NOME3 = "Nome 3";
        final String DEPOIMENTO1 = "Depoimento 1";
        final String DEPOIMENTO2 = "Depoimento 2";
        final String DEPOIMENTO3 = "Depoimento 3";
        final String FOTO1 = "Foto 1";
        final String FOTO2 = "Foto 2";
        final String FOTO3 = "Foto 3";

        List<Depoimento> depoimentos = new ArrayList<>();
        depoimentos.add(new Depoimento(ID1, NOME1, DEPOIMENTO1, FOTO1));
        depoimentos.add(new Depoimento(ID2, NOME2, DEPOIMENTO2, FOTO2));
        depoimentos.add(new Depoimento(ID3, NOME3, DEPOIMENTO3, FOTO3));

        Mockito.when(repository.findRandomThreeActive()).thenReturn(depoimentos);

        // act
        List<DepoimentoDto> depoimentoDtos = depoimentoService.findRandomThreeActive();

        // assert
        assertNotNull(depoimentoDtos);
        assertEquals(3, depoimentoDtos.size());
        assertEquals(NOME1, depoimentoDtos.get(0).nome());
        assertEquals(DEPOIMENTO1, depoimentoDtos.get(0).depoimento());
        assertEquals(FOTO1, depoimentoDtos.get(0).foto());
        assertEquals(NOME2, depoimentoDtos.get(1).nome());
        assertEquals(DEPOIMENTO2, depoimentoDtos.get(1).depoimento());
        assertEquals(FOTO2, depoimentoDtos.get(1).foto());
        assertEquals(NOME3, depoimentoDtos.get(2).nome());
        assertEquals(DEPOIMENTO3, depoimentoDtos.get(2).depoimento());
        assertEquals(FOTO3, depoimentoDtos.get(2).foto());
    }

    @Test
    void findByAtivoTrue_shouldReturnListOfDepoimentoDto() {

        // arrange
        final Long ID1 = 1L;
        final Long ID2 = 2L;
        final Long ID3 = 3L;
        final String NOME1 = "Nome 1";
        final String NOME2 = "Nome 2";
        final String NOME3 = "Nome 3";
        final String DEPOIMENTO1 = "Depoimento 1";
        final String DEPOIMENTO2 = "Depoimento 2";
        final String DEPOIMENTO3 = "Depoimento 3";
        final String FOTO1 = "Foto 1";
        final String FOTO2 = "Foto 2";
        final String FOTO3 = "Foto 3";

        List<Depoimento> depoimentos = new ArrayList<>();
        depoimentos.add(new Depoimento(ID1, NOME1, DEPOIMENTO1, FOTO1));
        depoimentos.add(new Depoimento(ID2, NOME2, DEPOIMENTO2, FOTO2));
        depoimentos.add(new Depoimento(ID3, NOME3, DEPOIMENTO3, FOTO3));
        Mockito.when(repository.findByAtivoTrue()).thenReturn(depoimentos);

        // act
        List<DepoimentoDto> depoimentoDtos = depoimentoService.findByAtivoTrue();

        // assert
        assertNotNull(depoimentoDtos);
        assertEquals(3, depoimentoDtos.size());
        assertEquals(NOME1, depoimentoDtos.get(0).nome());
        assertEquals(DEPOIMENTO1, depoimentoDtos.get(0).depoimento());
        assertEquals(FOTO1, depoimentoDtos.get(0).foto());
        assertEquals(NOME2, depoimentoDtos.get(1).nome());
        assertEquals(DEPOIMENTO2, depoimentoDtos.get(1).depoimento());
        assertEquals(FOTO2, depoimentoDtos.get(1).foto());
        assertEquals(NOME3, depoimentoDtos.get(2).nome());
        assertEquals(DEPOIMENTO3, depoimentoDtos.get(2).depoimento());
        assertEquals(FOTO3, depoimentoDtos.get(2).foto());
    }
}