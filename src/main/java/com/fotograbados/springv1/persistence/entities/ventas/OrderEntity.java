package com.fotograbados.springv1.persistence.entities.ventas;

import com.fotograbados.springv1.persistence.entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_purchase")
    private  Integer  idOrder;

    private String numero;

    @Column(name = "fecha_venta")
    private Date fechaVenta;

    @Column(name = "fecha_envio")
    private Date fechaEnvio;

    private Double descuento;

    private String estado;

    private double total;

                    //RELATIONS
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<BillEntity> bill;

    @ManyToOne
    @JoinColumn(name = "idCampañas", referencedColumnName = "id_campañas", insertable = false, updatable = false)
    private ChannelCampaign channelCamp;

    @OneToOne(mappedBy = "orderEntity")
    private ShippingDetail shippingDetails;

    @ManyToOne
    private Users users;



}
