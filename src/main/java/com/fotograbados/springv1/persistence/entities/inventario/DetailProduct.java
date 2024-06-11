package com.fotograbados.springv1.persistence.entities.inventario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_detalle", nullable = false)
    private Long idDetalle;
    @Column(name = "descripcion_producto",nullable = false)
    private String descripcionProducto;
    @Column(nullable = false)
    private String dimenciones;
    @Column(nullable = false)
    private String materiales;
    @Column(name = "imagen_detalles", nullable = false)
    private String detallesImagen;
    @Column(name = "nombre_user", nullable = false)
    private String nombreUser;
    @Column(name = "identifacion_user")
    private Integer identifacionUser;

                //RELATIONS
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    private Products products;
}
