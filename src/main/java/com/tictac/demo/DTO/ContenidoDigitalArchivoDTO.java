package com.tictac.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContenidoDigitalArchivoDTO {

    private String idDocente;

    private String titulo;

    private Integer visibilidad;

    private Integer lineaPPT;

    private String archivo;

    private String descripcion;

    private Integer poblaObjetivo;
}
