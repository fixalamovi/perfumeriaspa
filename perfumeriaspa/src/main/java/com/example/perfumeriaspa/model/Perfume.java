package com.example.perfumeriaspa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perfume {
    private Long id;
    private String nombre;
    private String marca;
    private Double precio;
    private Integer stock;
    private Long idCategoria; 
}
