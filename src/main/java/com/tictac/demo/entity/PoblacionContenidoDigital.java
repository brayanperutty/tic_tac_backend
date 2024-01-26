package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PoblacionContenidoDigital {

    @Id
    @Column(name="id_contenido_digital")
    private Integer idContenidoDigital;

    @Column(name="id_poblacion")
    private Integer idPoblacion;
}
