package com.example.perfumeriaspa.repository;

import org.springframework.stereotype.Repository;

import com.example.perfumeriaspa.model.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepository {

    private final List<Pedido> pedidos = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Pedido> findAll() { return pedidos; }

    public Optional<Pedido> findById(Long id) {
        return pedidos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Pedido> findByClienteId(Long clienteId) {
        return pedidos.stream().filter(p -> p.getClienteId().equals(clienteId)).toList();
    }

    public Pedido save(Pedido p) {
        if (p.getId() == null) { p.setId(idCounter++); pedidos.add(p); }
        else { pedidos.removeIf(old -> old.getId().equals(p.getId())); pedidos.add(p); }
        return p;
    }

    public void delete(Long id) { pedidos.removeIf(p -> p.getId().equals(id)); }
}