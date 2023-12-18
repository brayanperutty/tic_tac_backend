package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_competencia;

    @Basic
    private String nombre;

    public Competencia (){};
}
