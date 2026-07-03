package com.example.perfumeriaspa.service;

import com.example.perfumeriaspa.exception.RecursoNoEncontradoException;
import com.example.perfumeriaspa.exception.ReglaNegocioException;
import com.example.perfumeriaspa.model.Categoria;
import com.example.perfumeriaspa.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public List<Categoria> obtenerTodas() {
        return repository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con id: " + id));
    }

    public Categoria crear(Categoria categoria) {
        if (repository.existsByNombre(categoria.getNombre())) {
            throw new ReglaNegocioException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        }
        Categoria guardada = repository.save(categoria);
        log.info("Categoría creada con id: {}", guardada.getId());
        return guardada;
    }

    public Categoria actualizar(Long id, Categoria categoriaActualizada) {
        Categoria existente = obtenerPorId(id);
        existente.setNombre(categoriaActualizada.getNombre());
        existente.setTipo(categoriaActualizada.getTipo());
        existente.setDescripcion(categoriaActualizada.getDescripcion());
        log.info("Categoría actualizada con id: {}", id);
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        Categoria existente = obtenerPorId(id);
        repository.delete(existente);
        log.info("Categoría eliminada con id: {}", id);
    }
}
