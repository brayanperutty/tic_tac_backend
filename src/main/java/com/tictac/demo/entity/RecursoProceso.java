package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RecursoProceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="id_recurso")
    private Integer idRecurso;

    @Column(name="id_proceso")
    private Integer idProceso;

    public RecursoProceso(){};
}
