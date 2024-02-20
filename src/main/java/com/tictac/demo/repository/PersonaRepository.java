package com.tictac.demo.repository;

import com.tictac.demo.entity.LiderLinea;
import com.tictac.demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {

  List<Persona> findByIdInstitucion(Integer id);

  @Query(value = "SELECT i.id_institucion as id_institucion, i.nombre as nombre_inst, p.nombre || ' ' || p.apellido as nombre_docente, r.nombre, p.cedula as cedula " +
          "FROM persona p JOIN institucion i ON i.id_institucion = p.id_institucion " +
          "JOIN rol r ON r.id_rol = p.id_rol " +
          "WHERE p.codigo = :codigo AND p.password = :password AND p.id_rol = :idRol ", nativeQuery = true)
  List<Object[]> findByCodigoAndPasswordAndIdRol(String codigo, String password, Integer idRol);

  @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY d.numero_proyectos_sociales + d.numero_proyectos_ambiental + d.numero_proyectos_emprendimiento +  d.numero_proyectos_sexualidad + d.numero_proyectos_tic DESC), " +
          "p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, " +
          "(d.numero_proyectos_sociales + d.numero_proyectos_ambiental + d.numero_proyectos_emprendimiento +  d.numero_proyectos_sexualidad + d.numero_proyectos_tic) as proyectosRealizados " +
          "FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion " +
          "JOIN lider_linea l ON p.cedula = l.id_docente " +
          "JOIN linea_transversal lt ON l.id_linea = lt.id_linea " +
          "JOIN docente d ON p.cedula = d.id_docente " +
          "WHERE i.id_ciudad = :idCiudad LIMIT 3", nativeQuery = true)
  List<Object[]> findProyectosByMunicipio(Integer idCiudad);

  @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY d.numero_proyectos_sociales + d.numero_proyectos_ambiental + d.numero_proyectos_emprendimiento +  d.numero_proyectos_sexualidad + d.numero_proyectos_tic DESC), " +
          "p.nombre as nombrePersona, p.apellido as apellidoPersona, c.nombre as ciudadNombre, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, " +
          "(d.numero_proyectos_sociales + d.numero_proyectos_ambiental + d.numero_proyectos_emprendimiento +  d.numero_proyectos_sexualidad + d.numero_proyectos_tic) as proyectosRealizados " +
          "FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion " +
          "JOIN lider_linea l ON p.cedula = l.id_docente " +
          "JOIN linea_transversal lt ON l.id_linea = lt.id_linea " +
          "JOIN docente d ON p.cedula = d.id_docente " +
          "JOIN ciudad c ON i.id_ciudad = c.id_ciudad LIMIT 3", nativeQuery = true)
  List<Object[]> findProyectosByDepartamento();

  @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY d.numero_proyectos_sociales + d.numero_proyectos_ambiental + d.numero_proyectos_emprendimiento +  d.numero_proyectos_sexualidad + d.numero_proyectos_tic DESC), " +
          "p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, " +
          "(d.numero_proyectos_sociales + d.numero_proyectos_ambiental + d.numero_proyectos_emprendimiento +  d.numero_proyectos_sexualidad + d.numero_proyectos_tic) as proyectosRealizados " +
          "FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion " +
          "JOIN lider_linea l ON p.cedula = l.id_docente " +
          "JOIN linea_transversal lt ON l.id_linea = lt.id_linea " +
          "JOIN docente d ON p.cedula = d.id_docente " +
          "WHERE i.id_institucion = :idInstitucion", nativeQuery = true)
  List<Object[]> findProyectosByInstitucion(Integer idInstitucion);

  @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic DESC), " +
          "p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, " +
          "(d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic) as herramientasRealizadas " +
          "FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion " +
          "JOIN lider_linea l ON p.cedula = l.id_docente " +
          "JOIN linea_transversal lt ON l.id_linea = lt.id_linea " +
          "JOIN docente d ON p.cedula = d.id_docente " +
          "WHERE i.id_ciudad = :idCiudad " +
          "ORDER BY herramientasRealizadas DESC LIMIT 3", nativeQuery = true)
  List<Object[]> findHerramientasByMunicipio(Integer idCiudad);

  @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic DESC), " +
          "p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, " +
          "(d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic) as herramientasRealizadas " +
          "FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion " +
          "JOIN lider_linea l ON p.cedula = l.id_docente " +
          "JOIN linea_transversal lt ON l.id_linea = lt.id_linea " +
          "JOIN docente d ON p.cedula = d.id_docente " +
          "JOIN herramienta h ON h.docente_autor = p.cedula " +
          "WHERE i.id_ciudad = :idCiudad AND EXTRACT(YEAR FROM h.fecha_aprobacion) = :anio " +
          "ORDER BY herramientasRealizadas DESC LIMIT 3", nativeQuery = true)
  List<Object[]> findHerramientasByMunicipioFiltroAnio(Integer idCiudad, Integer anio);

  @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic DESC), " +
          "p.nombre as nombrePersona, p.apellido as apellidoPersona, c.nombre as ciudadNombre, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, " +
          "(d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic) as herramientasRealizadas " +
          "FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion " +
          "JOIN lider_linea l ON p.cedula = l.id_docente " +
          "JOIN linea_transversal lt ON l.id_linea = lt.id_linea " +
          "JOIN docente d ON p.cedula = d.id_docente " +
          "JOIN ciudad c ON i.id_ciudad = c.id_ciudad LIMIT 3", nativeQuery = true)
  List<Object[]> findHerramientasByDepartamento();

  @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic DESC), " +
          "p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, " +
          "(d.numero_herramientas_sociales + d.numero_herramientas_ambiental + d.numero_herramientas_emprendimiento +  d.numero_herramientas_sexualidad + d.numero_herramientas_tic) as herramientasRealizadas " +
          "FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion " +
          "JOIN lider_linea l ON p.cedula = l.id_docente " +
          "JOIN linea_transversal lt ON l.id_linea = lt.id_linea " +
          "JOIN docente d ON p.cedula = d.id_docente " +
          "WHERE i.id_institucion = :idInstitucion LIMIT 3", nativeQuery = true)
  List<Object[]> findHerramientasByInstitucion(Integer idInstitucion);

  @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY d.numero_contenidos_sociales + d.numero_contenidos_ambiental + d.numero_contenidos_emprendimiento +  d.numero_contenidos_sexualidad + d.numero_contenidos_tic DESC), " +
          "p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, " +
          "(d.numero_contenidos_sociales + d.numero_contenidos_ambiental + d.numero_contenidos_emprendimiento +  d.numero_contenidos_sexualidad + d.numero_contenidos_tic) as contenidosRealizados " +
          "FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion " +
          "JOIN lider_linea l ON p.cedula = l.id_docente " +
          "JOIN linea_transversal lt ON l.id_linea = lt.id_linea " +
          "JOIN docente d ON p.cedula = d.id_docente " +
          "WHERE i.id_ciudad = :idCiudad", nativeQuery = true)
  List<Object[]> findContenidosByMunicipio(Integer idCiudad);

  @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY d.numero_contenidos_sociales + d.numero_contenidos_ambiental + d.numero_contenidos_emprendimiento +  d.numero_contenidos_sexualidad + d.numero_contenidos_tic DESC), " +
          "p.nombre as nombrePersona, p.apellido as apellidoPersona, c.nombre as ciudadNombre, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, " +
          "(d.numero_contenidos_sociales + d.numero_contenidos_ambiental + d.numero_contenidos_emprendimiento +  d.numero_contenidos_sexualidad + d.numero_contenidos_tic) as contenidosRealizados " +
          "FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion " +
          "JOIN lider_linea l ON p.cedula = l.id_docente " +
          "JOIN linea_transversal lt ON l.id_linea = lt.id_linea " +
          "JOIN docente d ON p.cedula = d.id_docente " +
          "JOIN ciudad c ON i.id_ciudad = c.id_ciudad", nativeQuery = true)
  List<Object[]> findContenidosByDepartamento();

  @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY d.numero_contenidos_sociales + d.numero_contenidos_ambiental + d.numero_contenidos_emprendimiento +  d.numero_contenidos_sexualidad + d.numero_contenidos_tic DESC), " +
          "p.nombre as nombrePersona, p.apellido as apellidoPersona, i.nombre as nombreInstitucion, lt.nombre as lineaTransversal, " +
          "(d.numero_contenidos_sociales + d.numero_contenidos_ambiental + d.numero_contenidos_emprendimiento +  d.numero_contenidos_sexualidad + d.numero_contenidos_tic) as contenidosRealizados " +
          "FROM persona p JOIN institucion i ON p.id_institucion = i.id_institucion " +
          "JOIN lider_linea l ON p.cedula = l.id_docente " +
          "JOIN linea_transversal lt ON l.id_linea = lt.id_linea " +
          "JOIN docente d ON p.cedula = d.id_docente " +
          "WHERE i.id_institucion = :idInstitucion", nativeQuery = true)
  List<Object[]> findContenidosByInstitucion(Integer idInstitucion);


  @Query(value = "SELECT p.cedula as id, p.nombre || ' ' || p.apellido as nombre_docente, p.codigo as codigo, r.nombre as rol_nombre FROM persona p " +
                  "JOIN rol r ON r.id_rol = p.id_rol " +
                  "JOIN institucion i ON i.id_institucion = p.id_institucion " +
                 "WHERE p.id_institucion = :idInstitucion AND p.cedula <> :idDocente ORDER BY p.codigo", nativeQuery = true)
  List<Object[]>  listDocentesInstitucion(Integer idInstitucion, String idDocente);

  Optional<Persona> findByCodigo(String codigo);

  @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM lider_linea ll " +
                  "JOIN persona p ON p.cedula = ll.id_docente " +
                  "JOIN institucion i ON i.id_institucion = p.id_institucion " +
                  "WHERE ll.id_linea = :idLinea AND i.id_institucion = :idInstitucion AND ll.es_lider = 'true'", nativeQuery = true)
  Boolean findLiderExist(Integer idLinea, Integer idInstitucion);

  @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM lider_linea ll " +
          "JOIN persona p ON p.cedula = ll.id_docente " +
          "JOIN institucion i ON i.id_institucion = p.id_institucion " +
          "WHERE p.cedula = :idDocente AND ll.es_lider = 'true'", nativeQuery = true)
  Boolean findLider(String idDocente);

  @Query(value = "SELECT l.nombre as nombreLinea FROM lider_linea ll " +
          "JOIN persona p ON p.cedula = ll.id_docente " +
          "JOIN linea_transversal l ON l.id_linea = ll.id_linea " +
          "WHERE p.cedula = :idDocente AND ll.es_lider = 'true'", nativeQuery = true)
  String findLineaLider(String idDocente);
}
