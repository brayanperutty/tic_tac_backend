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
    private Integer id_proyecto;

    @Basic
    private Integer grado;
    private Integer id_tema;
    private String docente_lider;
    private String lecciones_aprendidas;
    private String nombre;
    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_inicio;
    private Date fecha_fin;

    public ProyectoAula(){};

}
