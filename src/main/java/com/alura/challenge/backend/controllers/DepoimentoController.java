package com.alura.challenge.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alura.challenge.backend.domain.dtos.DepoimentoDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoInsertDto;
import com.alura.challenge.backend.domain.dtos.DepoimentoUpdateDto;
import com.alura.challenge.backend.services.DepoimentoService;

@RestController
@RequestMapping
public class DepoimentoController {

    private final DepoimentoService service;

    public DepoimentoController(DepoimentoService service) {
        this.service = service;
    }

    @PostMapping("/depoimentos")
    public ResponseEntity<DepoimentoDto> create(@RequestBody @Validated DepoimentoInsertDto dto) {
        var depoimento = service.create(dto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(depoimento.id())
                .toUri();

        return ResponseEntity.created(location).body(depoimento);
    }

    @GetMapping("/depoimentos/{id}")
    public ResponseEntity<DepoimentoDto> findById(@PathVariable Long id) {
        var depoimento = service.findById(id);
        return ResponseEntity.ok().body(depoimento);
    }

    @GetMapping("/depoimentos-home")
    public ResponseEntity<List<DepoimentoDto>> findRandomThreeActive() {
        var depoimentos = service.findRandomThreeActive();

        return ResponseEntity.ok().body(depoimentos);
    }

    @GetMapping("/depoimentos")
    public ResponseEntity<List<DepoimentoDto>> findAll() {
        var depoimentos = service.findByAtivoTrue();

        return ResponseEntity.ok().body(depoimentos);
    }

    @PutMapping("/depoimentos/{id}")
    public ResponseEntity<DepoimentoDto> update(@PathVariable Long id, @RequestBody DepoimentoUpdateDto dto) {
        var depoimento = service.update(id, dto);

        return ResponseEntity.ok().body(depoimento);
    }

    @DeleteMapping("/depoimentos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
