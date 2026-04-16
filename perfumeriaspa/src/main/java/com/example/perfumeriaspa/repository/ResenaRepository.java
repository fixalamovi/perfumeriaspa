package com.example.perfumeriaspa.repository;

import org.springframework.stereotype.Repository;

import com.example.perfumeriaspa.model.Resena;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ResenaRepository {

    private final List<Resena> resenas = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Resena> findAll() { return resenas; }

    public Optional<Resena> findById(Long id) {
        return resenas.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public List<Resena> findByPerfumeId(Long perfumeId) {
        return resenas.stream().filter(r -> r.getPerfumeId().equals(perfumeId)).toList();
    }

    public List<Resena> findByClienteId(Long clienteId) {
        return resenas.stream().filter(r -> r.getClienteId().equals(clienteId)).toList();
    }

    public Resena save(Resena r) {
        if (r.getId() == null) { r.setId(idCounter++); resenas.add(r); }
        else { resenas.removeIf(old -> old.getId().equals(r.getId())); resenas.add(r); }
        return r;
    }

    public void delete(Long id) { resenas.removeIf(r -> r.getId().equals(id)); }
}