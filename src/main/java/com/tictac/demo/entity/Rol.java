package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="rol")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;

    @Basic
    private String nombre;
}
