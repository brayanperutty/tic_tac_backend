package com.tictac.demo.repository;

import com.tictac.demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {

  List<Persona> findByIdInstitucion(Integer id);

  Optional<Persona> findByCodigoAndPasswordAndIdRol(String codigo, String password, Integer idRol);

  @Query(value = "SELECT p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, (d.numero_proyectos_sociales + d.numero_proyectos_ambiental + d.numero_proyectos_emprendimiento +  d.numero_proyectos_sexualidad + d.numero_proyectos_tic) as proyectosRealizados FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion JOIN lider_linea l ON p.cedula = l.id_docente JOIN linea_transversal lt ON l.id_linea = lt.id_linea JOIN docente d ON p.cedula = d.id_docente WHERE i.id_ciudad = :idCiudad", nativeQuery = true)
  List<Object[]> findProyectosByMunicipio(Integer idCiudad);

  @Query(value = "SELECT p.nombre as nombrePersona, p.apellido as apellidoPersona, c.nombre as ciudadNombre, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, (d.numero_proyectos_sociales + d.numero_proyectos_ambiental + d.numero_proyectos_emprendimiento +  d.numero_proyectos_sexualidad + d.numero_proyectos_tic) as proyectosRealizados FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion JOIN lider_linea l ON p.cedula = l.id_docente JOIN linea_transversal lt ON l.id_linea = lt.id_linea JOIN docente d ON p.cedula = d.id_docente JOIN ciudad c ON i.id_ciudad = c.id_ciudad", nativeQuery = true)
  List<Object[]> findProyectosByDepartamento();

  @Query(value = "SELECT p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, (d.numero_proyectos_sociales + d.numero_proyectos_ambiental + d.numero_proyectos_emprendimiento +  d.numero_proyectos_sexualidad + d.numero_proyectos_tic) as proyectosRealizados FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion JOIN lider_linea l ON p.cedula = l.id_docente JOIN linea_transversal lt ON l.id_linea = lt.id_linea JOIN docente d ON p.cedula = d.id_docente WHERE i.id_institucion = :idInstitucion", nativeQuery = true)
  List<Object[]> findProyectosByInstitucion(Integer idInstitucion);

  @Query(value = "SELECT p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, (d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic) as herramientasRealizadas FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion JOIN lider_linea l ON p.cedula = l.id_docente JOIN linea_transversal lt ON l.id_linea = lt.id_linea JOIN docente d ON p.cedula = d.id_docente WHERE i.id_ciudad = :idCiudad", nativeQuery = true)
  List<Object[]> findHerramientasByMunicipio(Integer idCiudad);

  @Query(value = "SELECT p.nombre as nombrePersona, p.apellido as apellidoPersona, c.nombre as ciudadNombre, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, (d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic) as herramientasRealizadas FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion JOIN lider_linea l ON p.cedula = l.id_docente JOIN linea_transversal lt ON l.id_linea = lt.id_linea JOIN docente d ON p.cedula = d.id_docente JOIN ciudad c ON i.id_ciudad = c.id_ciudad", nativeQuery = true)
  List<Object[]> findHerramientasByDepartamento();

  @Query(value = "SELECT p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, (d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic) as herramientasRealizadas FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion JOIN lider_linea l ON p.cedula = l.id_docente JOIN linea_transversal lt ON l.id_linea = lt.id_linea JOIN docente d ON p.cedula = d.id_docente WHERE i.id_institucion = :idInstitucion", nativeQuery = true)
  List<Object[]> findHerramientasByInstitucion(Integer idInstitucion);

  @Query(value = "SELECT p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, (d.numero_contenidos_sociales + d.numero_contenidos_ambiental + d.numero_contenidos_emprendimiento +  d.numero_contenidos_sexualidad + d.numero_contenidos_tic) as contenidosRealizados FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion JOIN lider_linea l ON p.cedula = l.id_docente JOIN linea_transversal lt ON l.id_linea = lt.id_linea JOIN docente d ON p.cedula = d.id_docente WHERE i.id_ciudad = :idCiudad", nativeQuery = true)
  List<Object[]> findContenidosByMunicipio(Integer idCiudad);

  @Query(value = "SELECT p.nombre as nombrePersona, p.apellido as apellidoPersona, c.nombre as ciudadNombre, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, (d.numero_contenidos_sociales + d.numero_contenidos_ambiental + d.numero_contenidos_emprendimiento +  d.numero_contenidos_sexualidad + d.numero_contenidos_tic) as contenidosRealizados FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion JOIN lider_linea l ON p.cedula = l.id_docente JOIN linea_transversal lt ON l.id_linea = lt.id_linea JOIN docente d ON p.cedula = d.id_docente JOIN ciudad c ON i.id_ciudad = c.id_ciudad", nativeQuery = true)
  List<Object[]> findContenidosByDepartamento();

  @Query(value = "SELECT p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, (d.numero_contenidos_sociales + d.numero_contenidos_ambiental + d.numero_contenidos_emprendimiento +  d.numero_contenidos_sexualidad + d.numero_contenidos_tic) as contenidosRealizados FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion JOIN lider_linea l ON p.cedula = l.id_docente JOIN linea_transversal lt ON l.id_linea = lt.id_linea JOIN docente d ON p.cedula = d.id_docente WHERE i.id_institucion = :idInstitucion", nativeQuery = true)
  List<Object[]> findContenidosByInstitucion(Integer idInstitucion);



}
