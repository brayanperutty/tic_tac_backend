package com.tictac.demo.DTO.herramienta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MomentoDTO {

    private String nombre;

    private String descripcion;

    private List<ProcesosDTO> procesos;


}
