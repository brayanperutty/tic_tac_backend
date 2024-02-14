package com.tictac.demo.DTO;

import com.tictac.demo.entity.PlanTrabajo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanTrabajoDTO {

    private List<Map<String, ActividadesPlanDTO>> actividades;

    private InfoPlanTrabajoDTO infoPlanTrabajoPPT;
}
