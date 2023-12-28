package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_competencia")
    private Integer idCompetencia;

    @Column(name="nombre")
    private String nombre;

    public Competencia (){};
}
