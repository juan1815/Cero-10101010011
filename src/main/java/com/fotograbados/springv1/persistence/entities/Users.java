package com.fotograbados.springv1.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id_usario;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String password;
                //RELATIONS
    @OneToOne
    @JoinColumn(name = "rol_id_rol")
    private RolEntity rol;

}
