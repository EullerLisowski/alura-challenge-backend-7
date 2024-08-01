package com.alura.challenge.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.challenge.backend.domain.entities.Destino;

public interface DestinoRepository extends JpaRepository<Destino, Long> {
}
