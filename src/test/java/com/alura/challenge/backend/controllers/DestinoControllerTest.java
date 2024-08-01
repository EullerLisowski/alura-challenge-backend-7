package com.alura.challenge.backend.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alura.challenge.backend.domain.dtos.DestinoDto;
import com.alura.challenge.backend.domain.dtos.DestinoInsertDto;
import com.alura.challenge.backend.services.DestinoService;
import com.fasterxml.jackson.databind.ObjectMapper;

class DestinoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private DestinoService service;

    @InjectMocks
    private DestinoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Dado destinoInsertDto válido quando destinoController.create então httpStatus created deve ser retornado")
    void testDestinoControllerCreate() throws Exception {
        // arrange
        final Long ID = 2000L;
        final String NOME = "Nome Teste";
        final String FOTO = "Foto Teste";
        final Double PRECO = 100.00;

        DestinoInsertDto destinoInsertDto = new DestinoInsertDto(NOME, FOTO, PRECO);
        DestinoDto destinoDto = new DestinoDto(ID, NOME, FOTO, PRECO);
        given(service.create(any(DestinoInsertDto.class))).willReturn(destinoDto);

        // act & assert
        mockMvc.perform(post("/destinos").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(destinoInsertDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.nome").value(NOME))
                .andExpect(jsonPath("$.foto").value(FOTO))
                .andExpect(jsonPath("$.preco").value(PRECO));
    }
}
