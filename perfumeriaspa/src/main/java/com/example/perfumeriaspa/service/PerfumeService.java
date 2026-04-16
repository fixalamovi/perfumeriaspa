package com.example.perfumeriaspa.service;

import org.springframework.stereotype.Service;

import com.example.perfumeriaspa.model.Perfume;
import com.example.perfumeriaspa.repository.PerfumeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PerfumeService {

    private final PerfumeRepository repository;

    public PerfumeService(PerfumeRepository repository) {
        this.repository = repository;
    }

    public List<Perfume> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Perfume> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Perfume crearPerfume(Perfume perfume) {
        return repository.save(perfume);
    }

    public Optional<Perfume> actualizarPerfume(Long id, Perfume perfumeActualizado) {
        Optional<Perfume> existente = repository.findById(id);
        if (existente.isPresent()) {
            Perfume p = existente.get();
            p.setNombre(perfumeActualizado.getNombre());
            p.setMarca(perfumeActualizado.getMarca());
            p.setPrecio(perfumeActualizado.getPrecio());
            p.setStock(perfumeActualizado.getStock());
            p.setIdCategoria(perfumeActualizado.getIdCategoria());
            return Optional.of(p);
        }
        return Optional.empty();
    }

    public boolean eliminarPerfume(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.delete(id);
            return true;
        }
        return false;
    }
}