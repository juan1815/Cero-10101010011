package com.fotograbados.springv1.persistence.entities.ventas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "campaña_canal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campañas", nullable = false)
    private Long idCampañas;
    @Column(name = "nombre_plataformas", nullable = false)
    private String nombrePlataformas;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private Double costo;
    @Column(nullable = false)
    private String conversiones;
    @Column(nullable = false)
    private String estado;
    @Column(name = "fecha_uso",nullable = false)
    private LocalDateTime fechaUso;
    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

            //RELATIONS
    @OneToMany(mappedBy = "channelCamp", fetch = FetchType.EAGER)
    private List<OrderEntity> orderEntity;
}
