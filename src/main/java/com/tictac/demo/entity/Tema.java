package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tema;

    @Basic
    private String nombre;
    private Integer id_linea;
    private Integer id_competencia;
}
