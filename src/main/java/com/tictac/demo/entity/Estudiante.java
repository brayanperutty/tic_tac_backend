package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Estudiante {

    @Id
    @Column(name="id_estudiante")
    private String idEstudiante;

    private String nombre;

    private Integer grado;
}
