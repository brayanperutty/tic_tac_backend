package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class RecursoProceso {

    @Id
    private Integer id_recurso;

    @Basic
    private Integer id_proceso;

    public RecursoProceso(){};
}
