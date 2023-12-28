package com.tictac.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class PlanTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_plan")
    private Integer idPlan;

    @Column(name="id_linea")
    private Integer idLinea;

    @Column(name="nombre")
    private String nombre;

    @Column(name="lecciones_aprendidas")
    private String leccionesAprendidas;

    @Column(name="anio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date anio;

    public PlanTrabajo(){};
}
