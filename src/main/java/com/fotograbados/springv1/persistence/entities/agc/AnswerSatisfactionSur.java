package com.fotograbados.springv1.persistence.entities.agc;

import com.fotograbados.springv1.persistence.entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "respuesta_encuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSatisfactionSur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_answer", nullable = false)
    private Long idAnswer;

    @Column(nullable = false)
    private String respuesta;

    @Column(name = "fecha_encuesta",nullable = false)
    private LocalDate fechaEncuesta;


                //RELATIONS
    @OneToOne(fetch = FetchType.LAZY)
    private QuestionSatisfactionSur questionSSurs;

    @OneToOne(fetch = FetchType.LAZY)
    private OpinionProduct opinionProduct;

    @ManyToOne
    private Users usuario;

}
