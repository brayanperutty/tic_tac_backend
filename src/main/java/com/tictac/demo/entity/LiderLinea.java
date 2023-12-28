package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class LiderLinea {

    @Id
    private String idDocente;

    @Column(name="id_linea")
    private Integer idLinea;

    @Column(name="esLider")
    private Integer esLider;

    public LiderLinea (){};
}
