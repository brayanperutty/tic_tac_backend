package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Proceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_proceso")
    private Integer idProceso;

    @Column(name="id_momento")
    private Integer idMomento;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="tiempo")
    private String tiempo;

    public Proceso (){};

}
