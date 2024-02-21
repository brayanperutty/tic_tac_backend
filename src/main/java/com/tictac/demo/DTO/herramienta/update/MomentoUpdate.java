package com.tictac.demo.DTO.herramienta.update;

import com.tictac.demo.DTO.herramienta.ProcesosDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MomentoUpdate {

    private Integer idMomento;
    private String nombre;

    private String descripcion;

    private List<ProcesoUpdate> procesos;
}
