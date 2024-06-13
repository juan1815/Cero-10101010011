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
@Table(name = "stock_materiPri")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockMatPri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stockMat")
    private Long idStockMat;
    private Integer cantidad;
    private LocalDate fechaEntrada;

            //RELATIONS

    @ManyToOne(fetch = FetchType.LAZY)
    private Users usuario;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Products> products;

}
