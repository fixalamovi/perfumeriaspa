package com.example.perfumeriaspa.repository;

import org.springframework.stereotype.Repository;

import com.example.perfumeriaspa.model.Perfume;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PerfumeRepository {
    
    private List<Perfume> perfumes = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Perfume> findAll() {
        return perfumes;
    }

    public Optional<Perfume> findById(Long id) {
        return perfumes.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Perfume save(Perfume perfume) {
        if (perfume.getId() == null) {
            perfume.setId(idCounter++);
            perfumes.add(perfume);
        } else {
            perfumes.removeIf(p -> p.getId().equals(perfume.getId()));
            perfumes.add(perfume);
        }
        return perfume;
    }

    public void delete(Long id) {
        perfumes.removeIf(p -> p.getId().equals(id));
    }
}
