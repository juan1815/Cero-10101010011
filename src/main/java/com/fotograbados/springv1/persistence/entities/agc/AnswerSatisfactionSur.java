package com.fotograbados.springv1.persistence.entities.agc;

import com.fotograbados.springv1.persistence.entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "respuesta_encuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSatisfactionSur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_answer")
    private Long idAnswer;

    private String respuesta;

    private String comentarios;

    @Column(name = "fecha_encuesta")
    private Date fechaEncuesta;


                //RELATIONS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionSSurs", referencedColumnName = "id_pregunta")
    private QuestionSatisfactionSur question;

    @OneToOne(fetch = FetchType.LAZY)
    private OpinionProduct opinionProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Users usuario;

}
