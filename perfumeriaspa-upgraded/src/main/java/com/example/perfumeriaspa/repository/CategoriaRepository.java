package com.example.perfumeriaspa.repository;

import com.example.perfumeriaspa.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByTipo(String tipo);
    boolean existsByNombre(String nombre);
}
