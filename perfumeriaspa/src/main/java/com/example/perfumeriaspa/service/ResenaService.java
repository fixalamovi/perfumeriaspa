package com.example.perfumeriaspa.service;

import org.springframework.stereotype.Service;

import com.example.perfumeriaspa.model.Resena;
import com.example.perfumeriaspa.repository.ResenaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ResenaService {

    private final ResenaRepository repository;

    public ResenaService(ResenaRepository repository) {
        this.repository = repository;
    }

    public List<Resena> obtenerTodas() { return repository.findAll(); }

    public Optional<Resena> obtenerPorId(Long id) { return repository.findById(id); }

    public List<Resena> obtenerPorPerfume(Long perfumeId) {
        return repository.findByPerfumeId(perfumeId);
    }

    public List<Resena> obtenerPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public Resena crear(Resena r) {
        if (r.getFecha() == null) r.setFecha(LocalDate.now());
        return repository.save(r);
    }

    public Optional<Resena> actualizar(Long id, Resena r) {
        Optional<Resena> existente = repository.findById(id);
        if (existente.isPresent()) {
            Resena res = existente.get();
            res.setPuntuacion(r.getPuntuacion());
            res.setComentario(r.getComentario());
            return Optional.of(repository.save(res));
        }
        return Optional.empty();
    }

    public boolean eliminar(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.delete(id);
            return true;
        }
        return false;
    }
}