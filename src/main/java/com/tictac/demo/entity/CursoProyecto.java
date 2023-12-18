package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CursoProyecto {

    @Id
    private Integer grado;

    @Basic
    private Integer id_proyecto;
    private Integer id_actividad;

    public CursoProyecto (){};

}
