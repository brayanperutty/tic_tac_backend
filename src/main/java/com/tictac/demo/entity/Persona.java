package com.tictac.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Persona {

    @Id
    @Column(name="cedula")
    private String cedula;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="password")
    private String password;

    @Column(name="fechaNacimiento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @Column(name="codigo")
    private String codigo;

    @Column(name="id_rol")
    private Integer idRol;

    @Column(name="id_institucion")
    private Integer idInstitucion;

    public Persona (){}


}
