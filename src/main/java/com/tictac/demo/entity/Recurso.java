package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_recurso;

    @Basic
    private String tipo;
    private String url;
    private String nombre;

    public Recurso (){};
}
