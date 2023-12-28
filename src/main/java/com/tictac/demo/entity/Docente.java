package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Docente {

    @Id
    @Column(name="id_docente")
    private String idDocente;

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

    @Column(name="numero_contenidos_sociales")
    private Integer numeroContenidosSociales;

    @Column(name="numero_contenidos_sexualidad")
    private Integer numeroContenidosSexualidad;

    @Column(name="numero_contenidos_ambiental")
    private Integer numeroContenidosAmbiental;

    @Column(name="numero_contenidos_emprendimiento")
    private Integer numeroContenidosEmprendimiento;

    @Column(name="numero_contenidos_tic")
    private Integer numeroContenidosTic;

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

    public Docente (){};
}
