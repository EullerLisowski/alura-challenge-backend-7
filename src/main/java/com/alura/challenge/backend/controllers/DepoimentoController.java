package com.alura.challenge.backend.controllers;

import com.alura.challenge.backend.domain.dtos.DepoimentoInsertDto;
import com.alura.challenge.backend.services.DepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Validated DepoimentoInsertDto dto) {
        var depoimento = service.create(dto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(depoimento.id())
                .toUri();

        return ResponseEntity.created(location).body(depoimento);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable @Validated Long id) {
        var depoimento = service.findById(id);
        return ResponseEntity.ok().body(depoimento);
    }
}
