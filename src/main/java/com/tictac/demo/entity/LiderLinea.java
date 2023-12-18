package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class LiderLinea {

    @Id
    private String idDocente;

    @Basic
    private Integer id_linea;
    private Integer esLider;

    public LiderLinea (){};
}
