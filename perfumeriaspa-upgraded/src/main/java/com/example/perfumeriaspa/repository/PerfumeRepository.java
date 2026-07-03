package com.example.perfumeriaspa.repository;

import com.example.perfumeriaspa.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {
    List<Perfume> findByMarca(String marca);
    List<Perfume> findByIdCategoria(Long idCategoria);
    List<Perfume> findByGenero(String genero);
    List<Perfume> findByStockGreaterThan(Integer stock);
}
