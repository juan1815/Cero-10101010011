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
@NoArgsConstructor
@AllArgsConstructor
public class StockMatPri {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_stockMat")
    private Long idStockMat;
    private Integer cantidad;
    private LocalDate fechaEntrada;

            //RELATIONS

    @ManyToOne(fetch = FetchType.LAZY)
    private Users usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Products products;

}
