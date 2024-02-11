package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CursoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="id_proyecto")
    private Integer idProyecto;

    @Column(name="id_actividad")
    private Integer idActividad;

    public CursoProyecto (){};

}
