package com.alura.challenge.backend.services;

import com.alura.challenge.backend.domain.dtos.DepoimentoDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoInsertDto;
import com.alura.challenge.backend.domain.entities.Depoimento;
import com.alura.challenge.backend.repositories.DepoimentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepoimentoService {

    @Autowired
    private DepoimentoRepository repository;
    private final ModelMapper mapper = new ModelMapper();

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
}
