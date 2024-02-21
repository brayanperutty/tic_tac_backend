package com.tictac.demo.DTO.contenidoDigital.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContenidoDigitalUpdate {

    private Integer idContenido;

    private String titulo;

    private Integer visibilidad;

    private Integer lineaPPT;

    private String url;

    private String archivo;
    private String descripcion;

    private Integer poblaObjetivo;

    private String recomendaciones;
}
