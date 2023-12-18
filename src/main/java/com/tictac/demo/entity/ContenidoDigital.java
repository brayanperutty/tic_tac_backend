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
    private Integer id_contenido_digital;

    @Basic
    private String docente_autor;
    private String nombre_cont_digital;
    private Integer visibilidad;
    private Integer id_linea;
    private String estado;
    private String recomendacion;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_aprobacion;
    private Date fecha_creacion;
}
