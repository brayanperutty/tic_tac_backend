package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ActividadProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_actividad;

    @Basic
    private String nombre;
    private String descripcion;
    private String observaciones;
    private Integer cumplimiento;

    public ActividadProyecto (){};
}
