package com.example.perfumeriaspa.service;

import org.springframework.stereotype.Service;

import com.example.perfumeriaspa.model.Pedido;
import com.example.perfumeriaspa.repository.PedidoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> obtenerTodos() { return repository.findAll(); }

    public Optional<Pedido> obtenerPorId(Long id) { return repository.findById(id); }

    public List<Pedido> obtenerPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public Pedido crear(Pedido p) {
        if (p.getFecha() == null) p.setFecha(LocalDate.now());
        if (p.getEstado() == null || p.getEstado().isBlank()) p.setEstado("PENDIENTE");
        return repository.save(p);
    }

    public Optional<Pedido> actualizar(Long id, Pedido pedidoActualizado) {
        Optional<Pedido> existente = repository.findById(id);
        if (existente.isPresent()) {
            Pedido p = existente.get();
            p.setClienteId(pedidoActualizado.getClienteId());
            p.setPerfumeIds(pedidoActualizado.getPerfumeIds());
            p.setTotal(pedidoActualizado.getTotal());
            p.setEstado(pedidoActualizado.getEstado());
            return Optional.of(repository.save(p));
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