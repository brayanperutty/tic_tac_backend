package com.tictac.demo.DTO.contenidoDigital;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.io.File;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContenidoDigitalDTO {


    private String idDocente;

    private String titulo;

    private Integer visibilidad;

    private Integer lineaPPT;

    private String estado;

    private String url;

    private String descripcion;

    private Integer poblaObjetivo;
}
