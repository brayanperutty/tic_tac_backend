package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "situacion")
public class SituacionProblematica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descripcion;

    private String fecha;

    private Integer casos;

    private Integer linea;

    private String titulo;

    private Integer municipio;

    @Column(name = "id_plan")
    private Integer idPlan;
}
