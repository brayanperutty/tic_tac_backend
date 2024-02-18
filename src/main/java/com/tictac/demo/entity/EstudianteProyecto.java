package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EstudianteProyecto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="id_actividad")
    private Integer idActividad;

    @Column(name="id_estudiante")
    private String idEstudiante;


}
