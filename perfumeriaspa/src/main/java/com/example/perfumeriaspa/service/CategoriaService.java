package com.example.perfumeriaspa.service;

import org.springframework.stereotype.Service;

import com.example.perfumeriaspa.model.Categoria;
import com.example.perfumeriaspa.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> obtenerTodas() { return repository.findAll(); }

    public Optional<Categoria> obtenerPorId(Long id) { return repository.findById(id); }

    public Categoria crear(Categoria c) { return repository.save(c); }

    public Optional<Categoria> actualizar(Long id, Categoria c) {
        Optional<Categoria> existente = repository.findById(id);
        if (existente.isPresent()) {
            Categoria cat = existente.get();
            cat.setNombre(c.getNombre());
            cat.setTipo(c.getTipo());
            cat.setDescripcion(c.getDescripcion());
            return Optional.of(repository.save(cat));
        }
        return Optional.empty();
    }

    public boolean eliminar(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.delete(id);
            return true;
        }
        return false;
    }
}