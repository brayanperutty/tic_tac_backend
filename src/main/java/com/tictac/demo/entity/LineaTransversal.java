package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LineaTransversal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_linea")
    private Integer idLinea;

    @Column(name="nombre")
    private String nombre;

    public LineaTransversal (){};
}
