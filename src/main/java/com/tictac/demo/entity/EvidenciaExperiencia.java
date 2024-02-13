package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "evidencia_experiencia")
public class EvidenciaExperiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_experiencia")
    private Integer idExperiencia;

    private String recurso;
}
