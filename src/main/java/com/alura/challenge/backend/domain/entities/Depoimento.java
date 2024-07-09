package com.alura.challenge.backend.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "depoimentos")
@Entity(name = "Depoimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Depoimento {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String depoimento;

    @Column
    private String foto;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column
    private Boolean ativo;

    public Depoimento(Long id, String nome, String depoimento, String foto) {
        this.id = id;
        this.nome = nome;
        this.depoimento = depoimento;
        this.foto = foto;
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
    }
}
