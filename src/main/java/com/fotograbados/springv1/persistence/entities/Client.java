package com.fotograbados.springv1.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cliente", nullable = false)
    private Long id_factura;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "edad", nullable = false)
    private String edad;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "razon_social", nullable = true)
    private String razon_social;

    @Column(name = "sexo", nullable = true)
    private String sexo;

    @Column(name = "direccion", nullable = true)
    private String direccion;

    @Column(name = "telefono", nullable = true)
    private String telefono;

    @OneToOne
    @JoinColumn(name="rol_id_rol") //* debe conincidir con su PK */
    private RolEntity rol;

}
