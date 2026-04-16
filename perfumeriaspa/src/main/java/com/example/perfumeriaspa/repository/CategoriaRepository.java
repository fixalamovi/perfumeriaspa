package com.example.perfumeriaspa.repository;

import org.springframework.stereotype.Repository;

import com.example.perfumeriaspa.model.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository {

    private final List<Categoria> categorias = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Categoria> findAll() { return categorias; }

    public Optional<Categoria> findById(Long id) {
        return categorias.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Categoria save(Categoria c) {
        if (c.getId() == null) { c.setId(idCounter++); categorias.add(c); }
        else { categorias.removeIf(old -> old.getId().equals(c.getId())); categorias.add(c); }
        return c;
    }

    public void delete(Long id) { categorias.removeIf(c -> c.getId().equals(id)); }
}