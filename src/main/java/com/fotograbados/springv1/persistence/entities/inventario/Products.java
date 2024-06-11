package com.fotograbados.springv1.persistence.entities.inventario;

import com.fotograbados.springv1.persistence.entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto") //nullable = false
    private String nombreProducto;

    //@Column(nullable = false)
    private String descripcion;

   // @Column(nullable = false)
    private String imagen;

    //@Column(nullable = false)
    private double precio;

    //@Column(nullable = false)
    private Integer cantidad;


                                            //RELATIONS

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stockMat", referencedColumnName = "id_stockMat", insertable = false, updatable = false)
    private StockMatPri stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", insertable = false, updatable = false)
    private Category category;

    @OneToOne(mappedBy = "products")
    private DetailProduct detailProducts;

    @ManyToOne
    private Users usuario;



}
