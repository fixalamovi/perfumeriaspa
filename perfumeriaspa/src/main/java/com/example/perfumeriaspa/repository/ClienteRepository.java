package com.example.perfumeriaspa.repository;

import org.springframework.stereotype.Repository;

import com.example.perfumeriaspa.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {

    private final List<Cliente> clientes = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Cliente> findAll() { return clientes; }

    public Optional<Cliente> findById(Long id) {
        return clientes.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Cliente save(Cliente c) {
        if (c.getId() == null) { c.setId(idCounter++); clientes.add(c); }
        else { clientes.removeIf(old -> old.getId().equals(c.getId())); clientes.add(c); }
        return c;
    }

    public void delete(Long id) { clientes.removeIf(c -> c.getId().equals(id)); }
}