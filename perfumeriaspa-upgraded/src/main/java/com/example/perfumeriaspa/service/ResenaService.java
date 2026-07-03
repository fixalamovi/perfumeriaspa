package com.example.perfumeriaspa.service;

import com.example.perfumeriaspa.exception.RecursoNoEncontradoException;
import com.example.perfumeriaspa.exception.ReglaNegocioException;
import com.example.perfumeriaspa.model.Resena;
import com.example.perfumeriaspa.repository.ResenaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResenaService {

    private final ResenaRepository repository;

    public List<Resena> obtenerTodas() { return repository.findAll(); }

    public Resena obtenerPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Reseña no encontrada con id: " + id));
    }

    public List<Resena> obtenerPorPerfume(Long perfumeId) {
        return repository.findByPerfumeId(perfumeId);
    }

    public Resena crear(Resena resena) {
        if (resena.getPuntuacion() < 1 || resena.getPuntuacion() > 5) {
            throw new ReglaNegocioException("La puntuación debe estar entre 1 y 5");
        }
        resena.setFecha(LocalDate.now());
        Resena guardada = repository.save(resena);
        log.info("Reseña creada con id: {} para perfume: {}", guardada.getId(), guardada.getPerfumeId());
        return guardada;
    }

    public Resena actualizar(Long id, Resena resenaActualizada) {
        Resena existente = obtenerPorId(id);
        existente.setPuntuacion(resenaActualizada.getPuntuacion());
        existente.setComentario(resenaActualizada.getComentario());
        log.info("Reseña actualizada con id: {}", id);
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        Resena existente = obtenerPorId(id);
        repository.delete(existente);
        log.info("Reseña eliminada con id: {}", id);
    }
}
