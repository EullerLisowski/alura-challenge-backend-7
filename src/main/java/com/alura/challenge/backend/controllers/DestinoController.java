package com.alura.challenge.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alura.challenge.backend.domain.dtos.DestinoDto;
import com.alura.challenge.backend.domain.dtos.DestinoInsertDto;
import com.alura.challenge.backend.services.DestinoService;

@RestController
@RequestMapping
public class DestinoController {

    private final DestinoService service;

    public DestinoController(DestinoService service) {
        this.service = service;
    }

    @PostMapping("/destinos")
    public ResponseEntity<DestinoDto> create(@RequestBody @Validated DestinoInsertDto dto) {
        var destino = service.create(dto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(destino.id())
                .toUri();

        return ResponseEntity.created(location).body(destino);
    }

    @GetMapping("/destinos")
    public ResponseEntity<List<DestinoDto>> findAll() {
        var result = service.findAll();
        return ResponseEntity.ok().body(result);
    }

}
