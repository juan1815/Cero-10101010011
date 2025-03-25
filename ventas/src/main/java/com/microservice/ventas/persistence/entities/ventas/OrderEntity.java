package com.microservice.ventas.persistence.entities.ventas;

import com.microservice.ventas.persistence.entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Column (name = "id_pedido", nullable = false)
    private Integer id_pedido;
    @Column(name = "fecha_venta", nullable = false)
    private Date fecha_venta;
    @Column(name = "fecha_envio", nullable = false)
    private Date fecha_envio;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    
                    //RELATIONS
    // @ManyToOne
    // private Products products;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id_factura", nullable = false, referencedColumnName = "id_factura")
    private BillEntity bill;

    // @OneToOne(mappedBy = "pedido_id_factura", fetch = FetchType.EAGER)
    // private List<BillEntity> bill = new ArrayList<>(); ;
    //* pendiente añadir FK cliente */

    // @ManyToOne
    // @JoinColumn(name = "idCampañas", referencedColumnName = "id_campañas", insertable = false, updatable = false)
    // private ChannelCampaign channelCamp;

    // @ManyToOne
    // private Users users;

}
