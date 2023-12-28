package com.tictac.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ContenidoDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_contenido_digital")
    private Integer idContenidoDigital;

    @Column(name="docente_autor")
    private String docenteAutor;

    @Column(name="nombre_cont_digital")
    private String nombreContDigital;

    @Column(name="visibilidad")
    private Integer visibilidad;

    @Column(name="id_linea")
    private Integer idLinea;

    @Column(name="estado")
    private String estado;

    @Column(name="recomendacion")
    private String recomendacion;

    @Column(name="fecha_aprobacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaAprobacion;

    @Column(name="fecha_creacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacion;

    public ContenidoDigital(){};
}
