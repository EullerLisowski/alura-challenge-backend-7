package com.alura.challenge.backend.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.hibernate.mapping.List;
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

    @Test
    @DisplayName("Deve retornar uma lista de DestinoDto quando destinoController.findAll é chamado")
    void testDestinoControllerFindAll() throws Exception {
        // arrange
        final Long ID = 1000L;
        final String NOME = "Nome Teste";
        final String FOTO = "Foto Teste";
        final Double PRECO = 100.00;

        final Long ID2 = 2000L;
        final String NOME2 = "Nome Teste2";
        final String FOTO2 = "Foto Teste2";
        final Double PRECO2 = 200.00;

        final Long ID3 = 3000L;
        final String NOME3 = "Nome Teste3";
        final String FOTO3 = "Foto Teste3";
        final Double PRECO3 = 300.00;

        ArrayList<DestinoDto> destinoDtoList = new ArrayList<>();
        destinoDtoList.add(new DestinoDto(ID, NOME, FOTO, PRECO));
        destinoDtoList.add(new DestinoDto(ID2, NOME2, FOTO2, PRECO2));
        destinoDtoList.add(new DestinoDto(ID3, NOME3, FOTO3, PRECO3));

        given(service.findAll()).willReturn(destinoDtoList);

        // act & assert
        mockMvc.perform(get("/destinos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID))
                .andExpect(jsonPath("$[0].nome").value(NOME))
                .andExpect(jsonPath("$[0].foto").value(FOTO))
                .andExpect(jsonPath("$[0].preco").value(PRECO))
                .andExpect(jsonPath("$[1].id").value(ID2))
                .andExpect(jsonPath("$[1].nome").value(NOME2))
                .andExpect(jsonPath("$[1].foto").value(FOTO2))
                .andExpect(jsonPath("$[1].preco").value(PRECO2))
                .andExpect(jsonPath("$[2].id").value(ID3))
                .andExpect(jsonPath("$[2].nome").value(NOME3))
                .andExpect(jsonPath("$[2].foto").value(FOTO3))
                .andExpect(jsonPath("$[2].preco").value(PRECO3));
    }
}
