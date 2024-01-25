package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class PoblacionHerramienta {

    @Id
    @Column(name="id_poblacion")
    private Integer idPoblacion;

    @Column(name="id_herramienta")
    private Integer idHerramienta;

    private String nombre;
}
