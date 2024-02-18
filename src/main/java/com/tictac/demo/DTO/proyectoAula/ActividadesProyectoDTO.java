package com.tictac.demo.DTO.proyectoAula;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadesProyectoDTO {

        private String nombre;
        private String descripcion;
        private List<String> idEstudiante;

}
