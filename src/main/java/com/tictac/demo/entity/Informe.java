package com.tictac.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "informe")
public class Informe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "docente_autor")
    private String docenteAutor;

    @Column(name = "fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @Column(name = "recurso")
    private String recurso;
}
