package com.alura.challenge.backend.domain.dtos;

import jakarta.validation.constraints.NotEmpty;

public record DepoimentoInsertDto(
        @NotEmpty(message = "{NotEmpty.nome}")
        String nome,
        @NotEmpty(message = "{NotEmpty.depoimento}")
        String depoimento,
        @NotEmpty(message = "{NotEmpty.foto}")
        String foto) {
}
