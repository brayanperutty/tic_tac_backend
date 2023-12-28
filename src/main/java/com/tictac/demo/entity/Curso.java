package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Curso {

    @Id
    private Integer grado;

    @Column(name="jornada")
    private String jornada;

    public Curso (){};
}
