package com.alura.challenge.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "destinos")
@Entity(name = "Destino")
@Builder
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Destino {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String nome;

    @Column
    public String foto;

    @Column
    public Double preco;


    public Destino(Long id, String nome, String foto, Double preco) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.preco = preco;
    }

}
