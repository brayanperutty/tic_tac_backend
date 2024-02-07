package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "experiencias")
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    @Column(name="id_linea")
    private Integer idLinea;
}
