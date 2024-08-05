package com.alura.challenge.backend.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DestinoInsertDto(
        @NotEmpty(message = "{NotEmpty.nome}")
        String nome,

        @NotEmpty(message = "{NotEmpty.foto}")
        String foto,

        @NotNull(message = "{NotEmpty.preco}")
        Double preco) {
}
