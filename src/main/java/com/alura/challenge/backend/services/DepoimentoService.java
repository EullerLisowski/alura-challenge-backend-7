package com.alura.challenge.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.challenge.backend.domain.dtos.DepoimentoDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoInsertDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoUpdateDto;
import com.alura.challenge.backend.domain.entities.Depoimento;
import com.alura.challenge.backend.repositories.DepoimentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DepoimentoService {

    @Autowired
    private DepoimentoRepository repository;

    public DepoimentoDto create(DepoimentoInsertDto dto) {
        var depoimento = new Depoimento(null, dto.nome(), dto.depoimento(), dto.foto());
        depoimento = repository.save(depoimento);

        return new DepoimentoDto(depoimento.getId(), depoimento.getNome(), depoimento.getDepoimento(),
                depoimento.getFoto());
    }

    public DepoimentoDto findById(Long id) {
        var depoimento = repository.findByIdAndAtivoTrue(id).orElseThrow();

        return new DepoimentoDto(
                depoimento.getId(),
                depoimento.getNome(),
                depoimento.getDepoimento(),
                depoimento.getFoto());
    }

    public DepoimentoDto update(Long id, DepoimentoUpdateDto dto) {
        var result = repository.updateDepoimentoInfoById(id, dto.nome(), dto.depoimento(), dto.foto());

        if (result == 0) {
            throw new EntityNotFoundException("Depoimento não encontrado.");
        }

        return new DepoimentoDto(id, dto.nome(), dto.depoimento(), dto.foto());
    }

    public void delete(Long id) {
        var result = repository.updateDepoimentoDisabled(id);

        if (result == 0) {
            throw new EntityNotFoundException("Depoimento não encontrado.");
        }
    }

    public List<DepoimentoDto> findRandomThreeActive() {
        var result = repository.findRandomThreeActive();
        var depoimentos = new ArrayList<DepoimentoDto>();

        result.forEach(depoimento -> depoimentos.add(
                new DepoimentoDto(
                        depoimento.getId(),
                        depoimento.getNome(),
                        depoimento.getDepoimento(),
                        depoimento.getFoto())));

        return depoimentos;
    }

    public List<DepoimentoDto> findByAtivoTrue() {
        var result = repository.findByAtivoTrue();
        var depoimentos = new ArrayList<DepoimentoDto>();

        result.forEach(depoimento -> depoimentos.add(
                new DepoimentoDto(
                        depoimento.getId(),
                        depoimento.getNome(),
                        depoimento.getDepoimento(),
                        depoimento.getFoto())));

        return depoimentos;
    }
}
