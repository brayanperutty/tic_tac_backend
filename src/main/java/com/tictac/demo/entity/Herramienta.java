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
    private Integer id_herramienta;

    @Basic
    private Integer id_tema;
    private String docente_autor;
    private String nombre_herramienta;
    private String objetivos;
    private Integer visibilidad;
    private String estado;
    private String recomendacion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_aprobacion;
    private Date fecha_creacion;

    public Herramienta (){};
}
