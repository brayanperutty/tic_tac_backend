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

    private String anio;

    private String casos;

    private String descripcion;

    private String fecha;

    private String lineaPPT;

    private String nombrePlanTrabajo;

    private String titulo;

    private List<ActividadesPlanUpdate> actividades;

}
