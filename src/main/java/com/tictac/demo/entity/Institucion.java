package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Institucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_institucion")
    private Integer id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="id_ciudad")
    private Integer idCiudad;

    @Column(name="numero_proyectos_sociales")
    private Integer numeroProyectosSociales;
    @Column(name="numero_proyectos_sexualidad")
    private Integer numeroProyectosSexualidad;
    @Column(name="numero_proyectos_ambiental")
    private Integer numeroProyectosAmbiental;
    @Column(name="numero_proyectos_emprendimiento")
    private Integer numeroProyectosEmprendimiento;
    @Column(name="numero_proyectos_tic")
    private Integer numeroProyectosTic;

    @Column(name="numero_herramientas_sociales")
    private Integer numeroHerramientasSociales;
    @Column(name="numero_herramientas_sexualidad")
    private Integer numeroHerramientasSexualidad;
    @Column(name="numero_herramientas_ambiental")
    private Integer numeroHerramientasAmbiental;
    @Column(name="numero_herramientas_emprendimiento")
    private Integer numeroHerramientasEmprendimiento;
    @Column(name="numero_herramientas_tic")
    private Integer numeroHerramientasTic;


    public Institucion (){};





}
