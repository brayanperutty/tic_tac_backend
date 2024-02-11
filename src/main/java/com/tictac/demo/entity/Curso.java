package com.tictac.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="grado")
    private Integer grado;

    @Column(name="jornada")
    private String jornada;

    public Curso (){};
}
