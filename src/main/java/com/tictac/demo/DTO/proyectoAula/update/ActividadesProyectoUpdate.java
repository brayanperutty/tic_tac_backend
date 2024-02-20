package com.tictac.demo.DTO.proyectoAula.update;

import com.tictac.demo.entity.ActividadProyecto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadesProyectoUpdate {

    private Integer idActividad;

    private String nombre;

    private String descripcion;

    private String observaciones;

    private String cumplimiento;

    private List<String> idEstudiantes;
}
