package com.tictac.demo.DTO.planTrabajo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPlanTrabajoDTO {

    private String anio;

    private String casos;

    private String descripcion;

    private String fecha;

    private String lineaPPT;

    private String nombrePlanTrabajo;

    private String titulo;

    private Integer municipio;
}
