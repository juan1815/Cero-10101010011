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
    @Column(name = "nombre_producto")
    private String nombreProducto;
    private String descripcion;
    private String imagen;
    private double precio;
    private Integer cantidad;
                                            //RELATIONS


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Category category;
    @OneToOne(mappedBy = "products")
    private DetailProduct detailProducts;
    @ManyToOne
    private Users usuario;



}
