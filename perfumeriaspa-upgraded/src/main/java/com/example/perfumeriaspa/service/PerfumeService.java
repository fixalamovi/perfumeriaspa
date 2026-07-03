package com.example.perfumeriaspa.service;

import com.example.perfumeriaspa.exception.RecursoNoEncontradoException;
import com.example.perfumeriaspa.exception.ReglaNegocioException;
import com.example.perfumeriaspa.model.Perfume;
import com.example.perfumeriaspa.repository.PerfumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PerfumeService {

    private final PerfumeRepository repository;

    public List<Perfume> obtenerTodos() {
        log.info("Obteniendo todos los perfumes");
        return repository.findAll();
    }

    public Perfume obtenerPorId(Long id) {
        log.debug("Buscando perfume con id: {}", id);
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Perfume no encontrado con id: " + id));
    }

    public Perfume crearPerfume(Perfume perfume) {
        if (perfume.getPrecio() <= 0) {
            throw new ReglaNegocioException("El precio debe ser mayor a 0");
        }
        if (perfume.getStock() < 0) {
            throw new ReglaNegocioException("El stock no puede ser negativo");
        }
        Perfume guardado = repository.save(perfume);
        log.info("Perfume creado con id: {}, nombre: {}", guardado.getId(), guardado.getNombre());
        return guardado;
    }

    public Perfume actualizarPerfume(Long id, Perfume perfumeActualizado) {
        Perfume existente = obtenerPorId(id);
        existente.setNombre(perfumeActualizado.getNombre());
        existente.setMarca(perfumeActualizado.getMarca());
        existente.setPrecio(perfumeActualizado.getPrecio());
        existente.setStock(perfumeActualizado.getStock());
        existente.setIdCategoria(perfumeActualizado.getIdCategoria());
        existente.setTamano(perfumeActualizado.getTamano());
        existente.setGenero(perfumeActualizado.getGenero());
        existente.setDescripcion(perfumeActualizado.getDescripcion());
        existente.setFragancia(perfumeActualizado.getFragancia());
        Perfume actualizado = repository.save(existente);
        log.info("Perfume actualizado con id: {}", actualizado.getId());
        return actualizado;
    }

    public void eliminarPerfume(Long id) {
        Perfume existente = obtenerPorId(id);
        repository.delete(existente);
        log.info("Perfume eliminado con id: {}", id);
    }

    public List<Perfume> obtenerPorCategoria(Long categoriaId) {
        return repository.findByIdCategoria(categoriaId);
    }
}
