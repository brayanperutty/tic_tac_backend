package com.tictac.demo.DTO.herramienta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcesosDTO {

    private String descripcion;

    private List<Integer> recursos;

    private Integer tiempo;
}
