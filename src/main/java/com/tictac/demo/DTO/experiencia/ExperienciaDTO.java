package com.tictac.demo.DTO.experiencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciaDTO {

    private String descripcion;

    private List<String> images;

    private Integer lineaPPT;

    private String nombreExperiencia;

    private String docenteAutor;
}
