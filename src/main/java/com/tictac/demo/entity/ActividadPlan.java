package com.tictac.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_actividad;

    @Column(name="id_plan")
    private Integer idPlan;

    @Column(name="docente_apoyo")
    private String docenteApoyo;

    @Column(name="nombre")
    private String nombre;

    @Column(name="cumplimiento")
    private Integer cumplimiento;

    @Column(name="observaciones")
    private String observaciones;

    @Column(name="fecha_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @Column(name="fecha_fin")
    private Date fechaFin;

}
