package com.tictac.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ProyectoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_proyecto")
    private Integer idProyecto;

    @Column(name="grado")
    private Integer grado;

    @Column(name="id_tema")
    private Integer idTema;

    @Column(name="docente_lider")
    private String docenteLider;

    @Column(name="lecciones_aprendidas")
    private String leccionesAprendidas;

    @Column(name="nombre")
    private String nombre;

    @Column(name="fecha_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @Column(name="fecha_fin")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;

    public ProyectoAula(){};

}
