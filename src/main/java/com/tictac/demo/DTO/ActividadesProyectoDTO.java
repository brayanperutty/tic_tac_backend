package com.tictac.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadesProyectoDTO {

        private String nombre;
        private String descripcion;
        private Integer idEstudiante;

}
