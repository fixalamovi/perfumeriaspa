package com.example.perfumeriaspa.model;
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor;
import java.time.LocalDate; 
import java.util.List;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class Pedido {
    private Long id;
    private Long clienteId;
    private List<Long> perfumeIds;
    private LocalDate fecha;
    private Double total;
    private String estado;
}
