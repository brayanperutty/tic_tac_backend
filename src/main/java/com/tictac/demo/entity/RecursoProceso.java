package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class RecursoProceso {

    @Id
    @Column(name="id_recurso")
    private Integer idRecurso;

    @Column(name="id_proceso")
    private Integer idProceso;

    public RecursoProceso(){};
}
