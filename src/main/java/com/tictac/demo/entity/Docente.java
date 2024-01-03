package com.tictac.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
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

    public Docente (){
        this.numeroProyectosSociales = 0;
        this.numeroProyectosSexualidad = 0;
        this.numeroProyectosAmbiental = 0;
        this.numeroProyectosEmprendimiento = 0;
        this.numeroProyectosTic = 0;
        this.numeroContenidosSociales = 0;
        this.numeroContenidosSexualidad = 0;
        this.numeroContenidosAmbiental = 0;
        this.numeroContenidosEmprendimiento = 0;
        this.numeroContenidosTic = 0;
        this.numeroHerramientasSociales = 0;
        this.numeroHerramientasSexualidad = 0;
        this.numeroHerramientasAmbiental = 0;
        this.numeroHerramientasEmprendimiento = 0;
        this.numeroHerramientasTic = 0;
    }


}
