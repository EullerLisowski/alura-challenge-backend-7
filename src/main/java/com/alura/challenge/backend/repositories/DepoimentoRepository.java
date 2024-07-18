package com.alura.challenge.backend.repositories;

import com.alura.challenge.backend.domain.entities.Depoimento;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DepoimentoRepository extends JpaRepository<Depoimento, Long> {

    @Modifying
    @Transactional
    @Query("update Depoimento d set d.nome = :nome, d.depoimento = :depoimento, d.foto = :foto, d.dataAtualizacao = now() where d.id = :id")
    Integer updateDepoimentoInfoById(Long id, String nome, String depoimento, String foto);

    @Modifying
    @Transactional
    @Query("update Depoimento d set d.ativo = false, d.dataAtualizacao = now() where d.id = :id")
    Integer updateDepoimentoDisabled(Long id);

    @Query("select d from Depoimento d where d.ativo = true order by function('RAND') limit 3")
    List<Depoimento> findRandomThreeActive();

    List<Depoimento> findByAtivoTrue();

    Optional<Depoimento> findByIdAndAtivoTrue(Long id);
}
