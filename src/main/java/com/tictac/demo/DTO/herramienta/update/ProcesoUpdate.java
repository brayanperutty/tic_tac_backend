package com.tictac.demo.DTO.herramienta.update;

import com.tictac.demo.entity.Recurso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcesoUpdate {

    private Integer idProceso;
    private String descripcion;

    private List<Integer> recursos;

    private Integer tiempo;

}
