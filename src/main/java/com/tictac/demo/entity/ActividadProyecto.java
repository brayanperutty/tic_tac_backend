package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ActividadProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_actividad")
    private Integer idActividad;

    @Column(name="nombre")
    private String nombre;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="observaciones")
    private String observaciones;

    @Column(name="cumplimiento")
    private Integer cumplimiento;

    public ActividadProyecto (){};
}
