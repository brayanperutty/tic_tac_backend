package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tema")
    private Integer idTema;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "id_linea")
    private Integer idLinea;

    @Column(name = "id_competencia")
    private Integer idCompetencia;

    public Tema(){};
}
