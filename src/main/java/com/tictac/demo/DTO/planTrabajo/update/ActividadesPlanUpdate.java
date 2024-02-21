package com.tictac.demo.DTO.planTrabajo.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadesPlanUpdate {

    private Integer idActividad;
    private String actividad;

    private String descripcion;

    private String cumplimiento;

    private String observaciones;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;

    private String docentesApoyo;
}
