package com.microservice.ventas.persistence.entities.ventas;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "factura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false)
    private Long id_factura;
    @Column(name = "fecha_emision", nullable = false)
    private Date fecha_emision;
    @Column(name = "metodo_pago", nullable = true)
    private String metodo_pago;
    @Column(name = "valor_total", nullable = true)
    private Double valor_total;
    
    // @Column(nullable = false)
    // private double total;
}
