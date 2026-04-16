package com.example.perfumeriaspa.model;

import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class Resena {
    private Long id;
    private Long perfumeId;
    private Long clienteId;
    private Integer puntuacion;
    private String comentario;
    private LocalDate fecha;
}
