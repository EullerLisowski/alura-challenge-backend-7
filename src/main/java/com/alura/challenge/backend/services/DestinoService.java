package com.alura.challenge.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.challenge.backend.domain.dtos.DestinoDto;
import com.alura.challenge.backend.domain.dtos.DestinoInsertDto;
import com.alura.challenge.backend.domain.entities.Destino;
import com.alura.challenge.backend.repositories.DestinoRepository;

@Service
public class DestinoService {
    @Autowired
    private DestinoRepository repository;

    public DestinoDto create(DestinoInsertDto destinoInsertDto) {
        var destino = new Destino(null, destinoInsertDto.nome(), destinoInsertDto.foto(), destinoInsertDto.preco());
        destino = repository.save(destino);

        return new DestinoDto(destino.id, destino.nome, destino.foto, destino.preco);
    }

    public List<DestinoDto> findAll() {
        var results = repository.findAll();
        var destinos = new ArrayList<DestinoDto>();

        results.forEach(destino -> destinos.add(
                new DestinoDto(
                        destino.id,
                        destino.nome,
                        destino.foto,
                        destino.preco)));

        return destinos;
    }

}
