package com.tictac.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Herramienta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_herramienta")
    private Integer idHerramienta;

    @Column(name="id_tema")
    private Integer idTema;

    @Column(name="docente_autor")
    private String docenteAutor;

    @Column(name="nombre_herramienta")
    private String nombreHerramienta;
    private String objetivos;
    private Integer visibilidad;
    private String estado;
    private String recomendacion;

    @Column(name="fecha_aprobacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaprobacion;

    @Column(name="fecha_creacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacion;

    public Herramienta (){};
}
