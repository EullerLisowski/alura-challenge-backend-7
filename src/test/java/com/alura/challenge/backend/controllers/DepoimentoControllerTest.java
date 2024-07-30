package com.alura.challenge.backend.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alura.challenge.backend.domain.dtos.DepoimentoDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoInsertDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoUpdateDto;
import com.alura.challenge.backend.services.DepoimentoService;
import com.fasterxml.jackson.databind.ObjectMapper;

class DepoimentoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DepoimentoService service;

    @InjectMocks
    private DepoimentoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void givenValidDepoimentoInsertDto_whenCreate_thenReturnCreatedStatus() throws Exception {
        // arrange
        final Long ID = 1L;
        final String NOME = "Nome Teste";
        final String DEPOIMENTO = "Depoimento Teste";
        final String FOTO = "Foto Teste";

        DepoimentoInsertDto depoimentoInsertDto = new DepoimentoInsertDto(NOME, DEPOIMENTO, FOTO);
        DepoimentoDto depoimentoDto = new DepoimentoDto(ID, NOME, DEPOIMENTO, FOTO);
        given(service.create(any(DepoimentoInsertDto.class))).willReturn(depoimentoDto);

        // act & assert
        mockMvc.perform(post("/depoimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(depoimentoInsertDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.nome").value(NOME))
                .andExpect(jsonPath("$.depoimento").value(DEPOIMENTO))
                .andExpect(jsonPath("$.foto").value(FOTO));
    }

    @Test
    void givenValidDepoimentoUpdateDto_whenUpdate_thenReturnSuccess() throws Exception {
        // arrange
        final Long ID = 1L;
        final String NOME = "Nome Teste";
        final String DEPOIMENTO = "Depoimento Teste";
        final String FOTO = "Foto Teste";

        DepoimentoUpdateDto depoimentoUpdateDto = new DepoimentoUpdateDto(ID, NOME, DEPOIMENTO, FOTO);
        DepoimentoDto depoimentoDto = new DepoimentoDto(ID, NOME, DEPOIMENTO, FOTO);
        given(service.update(anyLong(), any(DepoimentoUpdateDto.class))).willReturn(depoimentoDto);

        // act & assert
        mockMvc.perform(put("/depoimentos/{id}", ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(depoimentoUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.nome").value(NOME))
                .andExpect(jsonPath("$.depoimento").value(DEPOIMENTO))
                .andExpect(jsonPath("$.foto").value(FOTO));
    }

    @Test
    void givenExistingDepoimentoId_whenFindById_thenReturnSuccess() throws Exception {
        // arrange
        final Long ID = 1L;
        final String NOME = "Nome Teste";
        final String DEPOIMENTO = "Depoimento Teste";
        final String FOTO = "Foto Teste";

        DepoimentoDto depoimentoDto = new DepoimentoDto(ID, NOME, DEPOIMENTO, FOTO);
        given(service.findById(anyLong())).willReturn(depoimentoDto);

        // act & assert
        mockMvc.perform(get("/depoimentos/{id}", ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(depoimentoDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.nome").value(NOME))
                .andExpect(jsonPath("$.depoimento").value(DEPOIMENTO))
                .andExpect(jsonPath("$.foto").value(FOTO));
    }

    @Test
    void givenThreeExistingDepoimentoId_whenFindThreeRandomActive_thenReturnSuccess() throws Exception {
        // arrange
        final Long ID = 1L;
        final String NOME = "Nome Teste";
        final String DEPOIMENTO = "Depoimento Teste";
        final String FOTO = "Foto Teste";
        final Long ID2 = 2L;
        final String NOME2 = "Nome Teste2";
        final String DEPOIMENTO2 = "Depoimento Teste2";
        final String FOTO2 = "Foto Teste2";
        final Long ID3 = 3L;
        final String NOME3 = "Nome Teste3";
        final String DEPOIMENTO3 = "Depoimento Teste3";
        final String FOTO3 = "Foto Teste3";

        DepoimentoDto depoimentoDto1 = new DepoimentoDto(ID, NOME, DEPOIMENTO, FOTO);
        DepoimentoDto depoimentoDto2 = new DepoimentoDto(ID2, NOME2, DEPOIMENTO2, FOTO2);
        DepoimentoDto depoimentoDto3 = new DepoimentoDto(ID3, NOME3, DEPOIMENTO3, FOTO3);
        List<DepoimentoDto> depoimentos = List.of(depoimentoDto1, depoimentoDto2, depoimentoDto3);

        given(service.findRandomThreeActive()).willReturn(depoimentos);

        // act & assert
        mockMvc.perform(get("/depoimentos-home")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(depoimentos)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID))
                .andExpect(jsonPath("$[0].nome").value(NOME))
                .andExpect(jsonPath("$[0].depoimento").value(DEPOIMENTO))
                .andExpect(jsonPath("$[0].foto").value(FOTO))
                .andExpect(jsonPath("$[1].id").value(ID2))
                .andExpect(jsonPath("$[1].nome").value(NOME2))
                .andExpect(jsonPath("$[1].depoimento").value(DEPOIMENTO2))
                .andExpect(jsonPath("$[1].foto").value(FOTO2))
                .andExpect(jsonPath("$[2].id").value(ID3))
                .andExpect(jsonPath("$[2].nome").value(NOME3))
                .andExpect(jsonPath("$[2].depoimento").value(DEPOIMENTO3))
                .andExpect(jsonPath("$[2].foto").value(FOTO3));
    }

    @Test
    void givenExistingDepoimentoId_whenFindAllActiveDepoimentos_thenReturnSuccess() throws Exception {
        // arrange
        final Long ID = 1L;
        final String NOME = "Nome Teste";
        final String DEPOIMENTO = "Depoimento Teste";
        final String FOTO = "Foto Teste";
        final Long ID2 = 2L;
        final String NOME2 = "Nome Teste2";
        final String DEPOIMENTO2 = "Depoimento Teste2";
        final String FOTO2 = "Foto Teste2";
        final Long ID3 = 3L;
        final String NOME3 = "Nome Teste3";
        final String DEPOIMENTO3 = "Depoimento Teste3";
        final String FOTO3 = "Foto Teste3";

        DepoimentoDto depoimentoDto1 = new DepoimentoDto(ID, NOME, DEPOIMENTO, FOTO);
        DepoimentoDto depoimentoDto2 = new DepoimentoDto(ID2, NOME2, DEPOIMENTO2, FOTO2);
        DepoimentoDto depoimentoDto3 = new DepoimentoDto(ID3, NOME3, DEPOIMENTO3, FOTO3);
        List<DepoimentoDto> depoimentos = List.of(depoimentoDto1, depoimentoDto2, depoimentoDto3);

        given(service.findByAtivoTrue()).willReturn(depoimentos);

        // act & assert
        mockMvc.perform(get("/depoimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(depoimentos)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID))
                .andExpect(jsonPath("$[0].nome").value(NOME))
                .andExpect(jsonPath("$[0].depoimento").value(DEPOIMENTO))
                .andExpect(jsonPath("$[0].foto").value(FOTO))
                .andExpect(jsonPath("$[1].id").value(ID2))
                .andExpect(jsonPath("$[1].nome").value(NOME2))
                .andExpect(jsonPath("$[1].depoimento").value(DEPOIMENTO2))
                .andExpect(jsonPath("$[1].foto").value(FOTO2))
                .andExpect(jsonPath("$[2].id").value(ID3))
                .andExpect(jsonPath("$[2].nome").value(NOME3))
                .andExpect(jsonPath("$[2].depoimento").value(DEPOIMENTO3))
                .andExpect(jsonPath("$[2].foto").value(FOTO3));
    }

    @Test
    void givenExistingDepoimentoId_whenDeleteById_thenReturnNoContent() throws Exception {
        // arrange
        final Long ID = 1L;

        doNothing().when(service).delete(ID);

        // act & assert
        mockMvc.perform(delete("/depoimentos/{id}", ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}