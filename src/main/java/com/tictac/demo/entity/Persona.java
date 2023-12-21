package com.tictac.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Persona {

    @Id
    private String cedula;

    @Basic
    private String nombre;
    private String apellido;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private String codigo;
    private Integer id_rol;
    private Integer id_institucion;

    public Persona (){};


}
