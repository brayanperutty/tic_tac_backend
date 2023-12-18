package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LineaTransversal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_linea;

    @Basic
    private String nombre;

    public LineaTransversal (){};
}
