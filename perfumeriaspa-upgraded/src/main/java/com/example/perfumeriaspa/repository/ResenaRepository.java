package com.example.perfumeriaspa.repository;

import com.example.perfumeriaspa.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByPerfumeId(Long perfumeId);
    List<Resena> findByClienteId(Long clienteId);
}
