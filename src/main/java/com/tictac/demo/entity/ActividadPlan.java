package com.tictac.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ActividadPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_actividad;

    @Basic
    private Integer id_plan;
    private String docente_apoyo;
    private String nombre;
    private Integer cumplimiento;
    private String observaciones;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_inicio;
    private Date fecha_fin;

    public ActividadPlan (){};




}
