package com.alura.challenge.backend.domain.dtos;

public record DepoimentoUpdateDto(
        Long id,
        String nome,
        String depoimento,
        String foto) {
}