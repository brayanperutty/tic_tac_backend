package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PoblacionHerramienta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="id_herramienta")
    private Integer idHerramienta;

    @Column(name="id_poblacion")
    private Integer idPoblacion;
}
