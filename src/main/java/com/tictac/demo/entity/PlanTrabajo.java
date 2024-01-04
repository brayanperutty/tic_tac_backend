package com.tictac.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_plan")
    private Integer idPlan;

    @Column(name="id_linea")
    private int idLinea;

    @Column(name="nombre")
    private String nombre;

    @Column(name="ano")
    private String anio;

    @Column(name="lecciones_aprendidas")
    private String leccionesAprendidas;

}
