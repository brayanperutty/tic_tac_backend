package com.tictac.demo.DTO.herramienta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HerramientaDTO {

    private String nombre;

    private Integer lineaPPT;

    private List<Integer> poblaObjetivo;

    private String tema;

    private String objetivo;

    private Integer competencias;

    private String idDocente;

    private Integer visibilidad;

    private String recomendaciones;

    private List<MomentoDTO> momentos;
}
