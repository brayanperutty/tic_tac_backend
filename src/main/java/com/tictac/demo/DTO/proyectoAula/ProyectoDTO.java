package com.tictac.demo.DTO.proyectoAula;

import com.tictac.demo.entity.ProyectoAula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoDTO {

    private ProyectoAula infoActividadProyectoPPT;
    private List<Map<String, ActividadesProyectoDTO>> actividades;

}
