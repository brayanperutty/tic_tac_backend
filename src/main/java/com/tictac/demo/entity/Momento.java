package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Momento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_momento;

    @Basic
    private Integer id_herramienta;
    private String nombre;
    private String descripcion;

    public Momento (){};
}
