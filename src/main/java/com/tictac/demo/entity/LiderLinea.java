package com.tictac.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class LiderLinea {

    @Id
    @Column(name="id_docente")
    private String idDocente;

    @Column(name="id_linea")
    private Integer idLinea;

    @Column(name = "fecha_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @Column(name = "es_lider")
    private Boolean esLider;

    public LiderLinea (){};
}
