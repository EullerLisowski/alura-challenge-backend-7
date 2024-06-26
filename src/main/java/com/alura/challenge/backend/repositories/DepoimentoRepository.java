package com.alura.challenge.backend.repositories;

import com.alura.challenge.backend.domain.entities.Depoimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepoimentoRepository extends JpaRepository<Depoimento, Long> {

}
