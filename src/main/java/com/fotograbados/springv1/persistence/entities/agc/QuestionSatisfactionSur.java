package com.fotograbados.springv1.persistence.entities.agc;

import com.fotograbados.springv1.persistence.entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pregunta_encuesta")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSatisfactionSur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta", nullable = false)
    private Long idPregunta;
    @Column(nullable = false)
    private String pregunta;
    @Column(name = "fecha_pregunta")
    private Date fechaPregunta;

            //RELATIONS
    @OneToMany(mappedBy = "question")
    private List<AnswerSatisfactionSur> answers;
    @ManyToOne
    private Users usuario;
}
