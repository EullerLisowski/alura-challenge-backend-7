package com.alura.challenge.backend.domain.entities;

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
}
