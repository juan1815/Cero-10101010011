package com.fotograbados.springv1.persistence.entities.agc;

import com.fotograbados.springv1.persistence.entities.Users;
import jakarta.persistence.*;

@Entity
@Table(name = "opinion_producto")
public class OpinionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_opinion")
    private Long idOpinion;

    private String comentario;

    private Integer calificacion;

    @ManyToOne
    private Users usuario;

}
