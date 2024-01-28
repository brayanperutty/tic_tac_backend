package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class LiderLinea {

    @Id
    @Column(name="id_docente")
    private String idDocente;

    @Column(name="id_linea")
    private Integer idLinea;

    @Column(name="eslider")
    private Integer esLider;

    public LiderLinea (){};
}
