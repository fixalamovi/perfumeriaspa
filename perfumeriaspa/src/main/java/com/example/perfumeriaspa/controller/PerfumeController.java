package com.example.perfumeriaspa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.perfumeriaspa.model.Perfume;
import com.example.perfumeriaspa.service.PerfumeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/perfumes")
@CrossOrigin(origins = "*") 
public class PerfumeController {

    private final PerfumeService service;

    public PerfumeController(PerfumeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Perfume>> getAll() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfume> getById(@PathVariable Long id) {
        Optional<Perfume> perfume = service.obtenerPorId(id);
        return perfume.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Perfume> create(@RequestBody Perfume perfume) {
        Perfume nuevoPerfume = service.crearPerfume(perfume);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPerfume);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfume> update(@PathVariable Long id, @RequestBody Perfume perfume) {
        Optional<Perfume> actualizado = service.actualizarPerfume(id, perfume);
        return actualizado.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.eliminarPerfume(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}