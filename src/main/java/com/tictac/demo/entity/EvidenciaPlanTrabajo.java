package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "evidencia_plan_trabajo")
public class EvidenciaPlanTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_plan")
    private Integer idPlan;

    private String recurso;
}
