package com.tictac.demo.DTO.planTrabajo.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPlanTrabajoUpdate {

    private Integer idPlan;

    private String lineaPPT;

    private String nombrePlanTrabajo;

    private String estado;

    private Boolean cierre;

    private String anio;
    private Integer idSituacion;

    private String descripcion;

    private String fecha;

    private String casos;

    private String titulo;

    private Integer municipio;

    private List<ActividadesPlanUpdate> actividades;

    private List<String> images;

}
