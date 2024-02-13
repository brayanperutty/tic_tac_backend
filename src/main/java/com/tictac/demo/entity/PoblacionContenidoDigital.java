package com.tictac.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PoblacionContenidoDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="id_contenido_digital")
    private Integer idContenidoDigital;

    @Column(name="id_poblacion")
    private Integer idPoblacion;
}
