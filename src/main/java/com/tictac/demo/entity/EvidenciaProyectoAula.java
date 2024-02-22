package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "evidencia_proyecto_aula")
public class EvidenciaProyectoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_proyecto")
    private Integer idProyecto;

    private String recurso;
}
