package com.example.perfumeriaspa.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "usuarios-service", url = "${usuarios.service.url}")
public interface UsuarioClient {

    @GetMapping("/api/usuarios/{id}")
    Map<String, Object> obtenerUsuarioPorId(@PathVariable Long id);

    @GetMapping("/api/usuarios/email/{email}")
    Map<String, Object> obtenerUsuarioPorEmail(@PathVariable String email);

    @PostMapping("/api/auth/login")
    Map<String, Object> login(@RequestBody Map<String, String> credenciales);

    @PostMapping("/api/usuarios")
    Map<String, Object> registrarUsuario(@RequestBody Map<String, Object> usuario);
}
