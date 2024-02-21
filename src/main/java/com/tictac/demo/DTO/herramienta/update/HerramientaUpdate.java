package com.tictac.demo.DTO.herramienta.update;

import com.tictac.demo.DTO.herramienta.MomentoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HerramientaUpdate {

    private Integer idHerramienta;
    private String nombre;

    private Integer lineaPPT;

    private List<Integer> poblaObjetivo;

    private String tema;
    private Integer idTema;

    private String objetivo;

    private Integer idCompetencia;

    private Integer visibilidad;

    private String recomendaciones;

    private List<MomentoUpdate> momentos;
}
