package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Docente {

    @Id
    private String id_docente;

    @Basic
    private Integer numero_proyectos_sociales, numero_proyectos_sexualidad, numero_proyectos_ambiental,
                    numero_proyectos_emprendimiento, numero_proyectos_tic;
    private Integer numero_contenidos_sociales, numero_contenidos_sexualidad, numero_contenidos_ambiental,
                    numero_contenidos_emprendimiento, numero_contenidos_tic;
    private Integer numero_herramientas_sociales, numero_herramientas_sexualidad, numero_herramientas_ambiental,
                    numero_herramientas_emprendimiento, numero_herramientas_tic;

    public Docente (){};
}
