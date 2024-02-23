package com.tictac.demo.DTO.planTrabajo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPlanTrabajoDTO {

    private String lineaPPT;

    private String nombrePlanTrabajo;

    private String anio;

    private String titulo;

    private String descripcion;
    private String casos;

    private String fecha;

    private Integer municipio;
}
