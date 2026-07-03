package com.example.perfumeriaspa.controller;

import com.example.perfumeriaspa.feign.UsuarioClient;
import com.example.perfumeriaspa.security.JwtUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UsuarioClient usuarioClient;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        log.info("Intento de login para usuario: {}", request.getUsername());

        try {
            log.info("Delegando autenticacion al microservicio de usuarios en 8081");
            Map<String, String> credenciales = Map.of(
                "username", request.getUsername(),
                "password", request.getPassword()
            );

            Map<String, Object> resultado = usuarioClient.login(credenciales);
            String token = (String) resultado.get("token");
            String role  = (String) resultado.get("role");

            log.info("Login exitoso via microservicio usuarios para: {}, rol: {}", request.getUsername(), role);

            return ResponseEntity.ok(Map.of(
                "token",    token,
                "username", request.getUsername(),
                "role",     role,
                "type",     "Bearer"
            ));

        } catch (Exception e) {
            log.warn("Credenciales invalidas o microservicio de usuarios no disponible: {}", e.getMessage());
            return ResponseEntity.status(401).body(
                Map.of("error", "Credenciales inválidas", "status", 401)
            );
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        log.info("Registro de nuevo usuario: {}", request.getUsername());

        try {
            log.info("Delegando registro al microservicio de usuarios en 8081");

            Map<String, Object> nuevoUsuario = Map.of(
                "username",     request.getUsername(),
                "password",     request.getPassword(),
                "email",        request.getEmail(),
                "nombre",       request.getNombre(),
                "apellido",     request.getApellido(),
                "rolId",        3  // CLIENTE por defecto
            );

            Map<String, Object> resultado = usuarioClient.registrarUsuario(nuevoUsuario);
            log.info("Usuario registrado exitosamente: {}", request.getUsername());

            return ResponseEntity.status(201).body(Map.of(
                "mensaje",  "Usuario registrado exitosamente",
                "username", request.getUsername(),
                "email",    request.getEmail(),
                "role",     "CLIENTE"
            ));

        } catch (Exception e) {
            log.warn("Error al registrar usuario: {}", e.getMessage());
            return ResponseEntity.status(400).body(
                Map.of("error", "No se pudo registrar el usuario. Puede que el username o email ya existan.", "status", 400)
            );
        }
    }

    @Data
    public static class LoginRequest {
        @NotBlank(message = "El username es obligatorio")
        private String username;

        @NotBlank(message = "La contraseña es obligatoria")
        private String password;
    }

    @Data
    public static class RegisterRequest {
        @NotBlank(message = "El username es obligatorio")
        private String username;

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 3, message = "La contraseña debe tener al menos 3 caracteres")
        private String password;

        @Email(message = "El email debe tener formato válido")
        @NotBlank(message = "El email es obligatorio")
        private String email;

        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;

        @NotBlank(message = "El apellido es obligatorio")
        private String apellido;
    }
}
