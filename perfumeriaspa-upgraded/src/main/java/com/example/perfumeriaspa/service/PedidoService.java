package com.example.perfumeriaspa.service;

import com.example.perfumeriaspa.exception.RecursoNoEncontradoException;
import com.example.perfumeriaspa.exception.ReglaNegocioException;
import com.example.perfumeriaspa.feign.UsuarioClient;
import com.example.perfumeriaspa.model.Pedido;
import com.example.perfumeriaspa.repository.ClienteRepository;
import com.example.perfumeriaspa.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final UsuarioClient usuarioClient;

    public List<Pedido> obtenerTodos() {
        return repository.findAll();
    }

    public Pedido obtenerPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Pedido no encontrado con id: " + id));
    }

    public List<Pedido> obtenerPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public Pedido crear(Pedido pedido) {
        // Validaciones de negocio
        if (pedido.getPerfumeIds() == null || pedido.getPerfumeIds().isEmpty()) {
            throw new ReglaNegocioException("El pedido debe contener al menos un perfume");
        }
        if (pedido.getTotal() <= 0) {
            throw new ReglaNegocioException("El total del pedido debe ser mayor a 0");
        }

        // Verificar que el cliente existe en perfumeria
        if (!clienteRepository.existsById(pedido.getClienteId())) {
            throw new RecursoNoEncontradoException("Cliente no encontrado con id: " + pedido.getClienteId());
        }

        // Comunicacion con microservicio de usuarios via Feign Client
        try {
            log.info("Consultando microservicio de usuarios para verificar usuario con id: {}", pedido.getClienteId());
            Map<String, Object> usuario = usuarioClient.obtenerUsuarioPorId(pedido.getClienteId());
            log.info("Usuario verificado en microservicio 8081: {}", usuario.get("username"));
        } catch (Exception e) {
            log.warn("Microservicio de usuarios no disponible, continuando sin validacion remota: {}", e.getMessage());
        }

        pedido.setFecha(LocalDate.now());
        pedido.setEstado("PENDIENTE");
        Pedido guardado = repository.save(pedido);
        log.info("Pedido creado con id: {} para cliente: {}", guardado.getId(), guardado.getClienteId());
        return guardado;
    }

    public Pedido actualizar(Long id, Pedido pedidoActualizado) {
        Pedido existente = obtenerPorId(id);
        if ("CANCELADO".equals(existente.getEstado()) || "ENTREGADO".equals(existente.getEstado())) {
            throw new ReglaNegocioException("No se puede modificar un pedido en estado: " + existente.getEstado());
        }
        existente.setEstado(pedidoActualizado.getEstado());
        existente.setTotal(pedidoActualizado.getTotal());
        existente.setPerfumeIds(pedidoActualizado.getPerfumeIds());
        log.info("Pedido actualizado con id: {}", id);
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        Pedido existente = obtenerPorId(id);
        repository.delete(existente);
        log.info("Pedido eliminado con id: {}", id);
    }
}
