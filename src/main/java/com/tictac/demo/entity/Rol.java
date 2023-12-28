package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="rol")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rol")
    private Integer idRol;

    @Column(name="nombre")
    private String nombre;

    public Rol (){};
}
