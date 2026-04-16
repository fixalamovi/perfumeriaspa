package com.example.perfumeriaspa.service;

import org.springframework.stereotype.Service;

import com.example.perfumeriaspa.model.Cliente;
import com.example.perfumeriaspa.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> obtenerTodos() { return repository.findAll(); }

    public Optional<Cliente> obtenerPorId(Long id) { return repository.findById(id); }

    public Cliente crear(Cliente nuevoCliente) { return repository.save(nuevoCliente); }

    public Optional<Cliente> actualizar(Long id, Cliente clienteActualizado) {
        Optional<Cliente> existente = repository.findById(id);
        if (existente.isPresent()) {
            Cliente c = existente.get();
            c.setNombre(clienteActualizado.getNombre());
            c.setApellido(clienteActualizado.getApellido());
            c.setEmail(clienteActualizado.getEmail());
            c.setTelefono(clienteActualizado.getTelefono());
            c.setDireccion(clienteActualizado.getDireccion());
            return Optional.of(repository.save(c));
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