package com.example.perfumeriaspa.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El cliente es obligatorio")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @ElementCollection
    @CollectionTable(name = "pedido_perfumes",
        joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "perfume_id")
    private List<Long> perfumeIds;

    @Column(nullable = false)
    private LocalDate fecha;

    @NotNull(message = "El total es obligatorio")
    @Positive(message = "El total debe ser mayor a 0")
    @Column(nullable = false)
    private Double total;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(PENDIENTE|CONFIRMADO|ENTREGADO|CANCELADO)$",
             message = "Estado inválido. Use: PENDIENTE, CONFIRMADO, ENTREGADO o CANCELADO")
    @Column(nullable = false)
    private String estado;
}
