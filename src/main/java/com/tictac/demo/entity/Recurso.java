package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_recurso")
    private Integer idRecurso;

    @Column(name="tipo")
    private String tipo;

    @Column(name="nombre")
    private String nombre;

    public Recurso (){};
}
