package com.tictac.demo.DTO.planTrabajo.update;

import com.tictac.demo.entity.PlanTrabajo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
