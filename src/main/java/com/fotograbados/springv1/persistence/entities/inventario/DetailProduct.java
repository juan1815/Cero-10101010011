package com.fotograbados.springv1.persistence.entities.inventario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detail_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_detail", nullable = false)
    private Long idDetail;
    @Column(name = "date_fabrication",nullable = false)
    private String dataFabrication;
                //RELATIONS
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inventory_fk", referencedColumnName = "id_product", insertable = false, updatable = false)
    private Products products;

    @OneToOne(mappedBy = "detailPro")
    private DetailProduct inventory;
}
