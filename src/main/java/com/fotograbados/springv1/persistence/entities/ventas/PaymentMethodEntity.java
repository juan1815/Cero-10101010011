package com.fotograbados.springv1.persistence.entities.ventas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "metodo_pago")
@Getter
@Setter
@NoArgsConstructor
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_metodo_pago")
    private Long idMetodoPa;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;
    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;
            //RELATIONS
    @OneToOne(fetch = FetchType.LAZY)
    private BillEntity bill;
}
