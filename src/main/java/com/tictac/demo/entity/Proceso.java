package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Proceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_proceso;

    @Basic
    private Integer id_momento;
    private String descripcion;
    private String tiempo;

    public Proceso (){};

}
