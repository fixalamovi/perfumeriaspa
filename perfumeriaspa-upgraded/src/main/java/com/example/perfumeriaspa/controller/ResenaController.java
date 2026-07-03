package com.example.perfumeriaspa.controller;

import com.example.perfumeriaspa.model.Resena;
import com.example.perfumeriaspa.service.ResenaService;
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
@RequestMapping("/api/resenas")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService service;

    @GetMapping
    public ResponseEntity<List<Resena>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/perfume/{perfumeId}")
    public ResponseEntity<List<Resena>> obtenerPorPerfume(@PathVariable Long perfumeId) {
        return ResponseEntity.ok(service.obtenerPorPerfume(perfumeId));
    }

    @PostMapping
    public ResponseEntity<Resena> crear(@Valid @RequestBody Resena resena) {
        return ResponseEntity.status(201).body(service.crear(resena));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resena> actualizar(@PathVariable Long id,
                                              @Valid @RequestBody Resena resena) {
        return ResponseEntity.ok(service.actualizar(id, resena));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
