package com.fotograbados.springv1.persistence.entities.ventas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_envio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalleenv")
    private Long iddetalleenv;
    @Column(name = "tipo_envio")
    private String tipoEnvio;
    @Column(nullable = false)
    private String descripcion;
    @Column(name = "costo_envio")
    private Double costoEnvio;

    @OneToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

}
