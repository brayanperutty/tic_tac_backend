package com.tictac.demo.DTO.proyectoAula.update;

import com.tictac.demo.entity.ProyectoAula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoProyectoUpdate {

    private ProyectoAula infoActividadProyectoPPT;

    private List<ActividadesProyectoUpdate> actividades;

    private List<String> images;
}
