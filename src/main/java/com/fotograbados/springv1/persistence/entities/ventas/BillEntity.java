package com.fotograbados.springv1.persistence.entities.ventas;

import com.fotograbados.springv1.persistence.entities.inventario.Products;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_factura", nullable = false)
    private Long idfactura;
    @Column(name = "producto_nombre", nullable = false)
    private String productoNombre;
    @Column(nullable = false)
    private Integer cantidad;
    @Column(name = "costo_unidad")
    private double costoUnidad;
    @Column(nullable = false)
    private double total;
                //RELATIONS
   @ManyToOne
   @JoinColumn(name = "id_order", referencedColumnName = "id_purchase", insertable = false, updatable = false)
   private OrderEntity order;

    @OneToOne(mappedBy = "bill")
    private PaymentMethodEntity paymentMethodEntities;

    @ManyToOne
    private Products producto;

}
