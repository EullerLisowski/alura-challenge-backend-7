package com.alura.challenge.backend.services;

import com.alura.challenge.backend.domain.dtos.DepoimentoDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoInsertDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoUpdateDto;
import com.alura.challenge.backend.domain.entities.Depoimento;
import com.alura.challenge.backend.repositories.DepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class DepoimentoService {

    @Autowired
    private DepoimentoRepository repository;

    public DepoimentoDto create(DepoimentoInsertDto dto) {
        var depoimento = new Depoimento(null, dto.nome(), dto.depoimento(), dto.foto());
        depoimento = repository.save(depoimento);

        return new DepoimentoDto(depoimento.getId(), depoimento.getNome(), depoimento.getDepoimento(), depoimento.getFoto());
    }

    public DepoimentoDto findById(Long id) {
        var depoimento = repository.findById(id).orElseThrow();

        return new DepoimentoDto(
                depoimento.getId(),
                depoimento.getNome(),
                depoimento.getDepoimento(),
                depoimento.getFoto()
        );
    }

    public DepoimentoDto update(Long id, DepoimentoUpdateDto dto) {
        var depoimento = new Depoimento(id, dto.nome(), dto.depoimento(), dto.foto());
        depoimento = repository.save(depoimento);

        return new DepoimentoDto(depoimento.getId(), depoimento.getNome(), depoimento.getDepoimento(), depoimento.getFoto());
    }
}
