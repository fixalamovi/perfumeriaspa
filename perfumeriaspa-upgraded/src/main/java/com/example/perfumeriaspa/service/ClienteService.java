package com.example.perfumeriaspa.service;

import com.example.perfumeriaspa.exception.RecursoNoEncontradoException;
import com.example.perfumeriaspa.exception.ReglaNegocioException;
import com.example.perfumeriaspa.model.Cliente;
import com.example.perfumeriaspa.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public List<Cliente> obtenerTodos() {
        log.info("Listando todos los clientes");
        return repository.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con id: " + id));
    }

    public Cliente crear(Cliente cliente) {
        if (repository.existsByEmail(cliente.getEmail())) {
            throw new ReglaNegocioException("Ya existe un cliente con el email: " + cliente.getEmail());
        }
        Cliente guardado = repository.save(cliente);
        log.info("Cliente creado con id: {}", guardado.getId());
        return guardado;
    }

    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        Cliente existente = obtenerPorId(id);
        if (!existente.getEmail().equals(clienteActualizado.getEmail())
                && repository.existsByEmail(clienteActualizado.getEmail())) {
            throw new ReglaNegocioException("El email ya está en uso: " + clienteActualizado.getEmail());
        }
        existente.setNombre(clienteActualizado.getNombre());
        existente.setApellido(clienteActualizado.getApellido());
        existente.setEmail(clienteActualizado.getEmail());
        existente.setTelefono(clienteActualizado.getTelefono());
        existente.setDireccion(clienteActualizado.getDireccion());
        log.info("Cliente actualizado con id: {}", id);
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        Cliente existente = obtenerPorId(id);
        repository.delete(existente);
        log.info("Cliente eliminado con id: {}", id);
    }
}
