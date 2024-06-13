package com.fotograbados.springv1.persistence.entities.inventario;

import com.fotograbados.springv1.persistence.entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "tipo_categoria")
    private String tipoCategoria;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "estado_categoria",nullable = false)
    private String estadoCategoria;

    @Column(name = "fecha_cambio")
    private LocalDate fechaCambio;

                                   //RELATIONS

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Products> products;

    @ManyToOne
    private Users usuario;


}
