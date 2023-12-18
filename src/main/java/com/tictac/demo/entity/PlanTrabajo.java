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
    private Integer id_plan;

    @Basic
    private Integer id_linea;
    private String nombre;
    private String lecciones_aprendidas;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date anio;

    public PlanTrabajo(){};
}
