package com.fotograbados.springv1.persistence.entities;

import com.fotograbados.springv1.persistence.entities.agc.AnswerSatisfactionSur;
import com.fotograbados.springv1.persistence.entities.agc.OpinionProduct;
import com.fotograbados.springv1.persistence.entities.agc.QuestionSatisfactionSur;
import com.fotograbados.springv1.persistence.entities.inventario.Category;
import com.fotograbados.springv1.persistence.entities.inventario.Products;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String username;
    private String email;
    private String direccion;
    private String telefono;
    private String genero;
    private String codigoPostal;
    //private String tipo;
    private String password;
    private String avatar;
                //RELATIONS
    @OneToMany(mappedBy = "usuario")
    private List<Products> productos;

    @OneToMany(mappedBy = "users")
    private List<OrderEntity> orderEntities;

    @OneToMany(mappedBy = "usuario")
    private List<Category> categories;

    @OneToMany(mappedBy = "usuario")
    private List<AnswerSatisfactionSur> answerSatisfactionSurs;

    @OneToMany(mappedBy = "usuario")
    private List<QuestionSatisfactionSur> questionSatisfactionSurs;

    @OneToMany(mappedBy = "usuario")
    private List<OpinionProduct> opinionProducts;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private RolEntity rolEntity;

}
