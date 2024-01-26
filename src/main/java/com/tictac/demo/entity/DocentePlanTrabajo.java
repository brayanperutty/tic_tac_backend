package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class DocentePlanTrabajo {

    @Id
    @Column(name="id_actividad_plan")
    private Integer idActividadPlan;

    @Column(name="id_docente")
    private String idDocente;


}
