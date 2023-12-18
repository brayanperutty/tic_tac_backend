package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Institucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_institucion;

    @Basic
    private String nombre;

    @Basic
    private Integer id_ciudad;

    @Basic
    private Integer numero_proyectos_sociales, numero_proyectos_sexualidad,
            numero_proyectos_ambiental, numero_proyectos_emprendimiento, numero_proyectos_tic;

    @Basic
    private Integer numero_herramientas_sociales, numero_herramientas_sexualidad,
                    numero_herramientas_ambiental, numero_herramientas_emprendimiento, numero_herramientas_tic;

    public Institucion (){};





}
