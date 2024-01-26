package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Poblacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_poblacion")
    private Integer idPoblacion;

    private String nombre;
}
