package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CursoProyecto {

    @Id
    private Integer grado;

    @Column(name="id_proyecto")
    private Integer idProyecto;

    @Column(name="id_actividad")
    private Integer idActividad;

    public CursoProyecto (){};

}
