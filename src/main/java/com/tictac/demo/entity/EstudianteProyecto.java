package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class EstudianteProyecto {

    @Id
    @Column(name="id_estudiante")
    private String idEstudiante;

    @Column(name="id_proyecto")
    private Integer idProyecto;
}
