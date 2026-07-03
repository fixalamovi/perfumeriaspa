package com.example.perfumeriaspa.controller;

import com.example.perfumeriaspa.model.Perfume;
import com.example.perfumeriaspa.service.PerfumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/perfumes")
@RequiredArgsConstructor
public class PerfumeController {

    private final PerfumeService service;

    @GetMapping
    public ResponseEntity<List<Perfume>> obtenerTodos() {
        log.info("GET /api/perfumes");
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfume> obtenerPorId(@PathVariable Long id) {
        log.info("GET /api/perfumes/{}", id);
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Perfume>> obtenerPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(service.obtenerPorCategoria(categoriaId));
    }

    @PostMapping
    public ResponseEntity<Perfume> crear(@Valid @RequestBody Perfume perfume) {
        log.info("POST /api/perfumes - nombre: {}", perfume.getNombre());
        return ResponseEntity.status(201).body(service.crearPerfume(perfume));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfume> actualizar(@PathVariable Long id,
                                               @Valid @RequestBody Perfume perfume) {
        log.info("PUT /api/perfumes/{}", id);
        return ResponseEntity.ok(service.actualizarPerfume(id, perfume));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/perfumes/{}", id);
        service.eliminarPerfume(id);
        return ResponseEntity.noContent().build();
    }
}
