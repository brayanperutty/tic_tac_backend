package com.tictac.demo.repository;

import com.tictac.demo.entity.ProyectoAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProyectoAulaRepository extends JpaRepository<ProyectoAula, Integer> {

    @Query(value = "SELECT SUM(numero_proyectos_ambiental) AS ambiental, " +
            "SUM(numero_proyectos_sexualidad) AS sexualidad, " +
            "SUM(numero_proyectos_sociales) AS sociales, " +
            "SUM(numero_proyectos_emprendimiento) AS emprendimiento, " +
            "SUM(numero_proyectos_tic) AS TIC " +
            "FROM institucion", nativeQuery = true)
    List<Object[]> findTotalProyectosDeAula();

    @Query(value = "SELECT SUM(numero_proyectos_ambiental) AS ambiental, " +
            "SUM(numero_proyectos_sexualidad) AS sexualidad, " +
            "SUM(numero_proyectos_sociales) AS sociales, " +
            "SUM(numero_proyectos_emprendimiento) AS emprendimiento, " +
            "SUM(numero_proyectos_tic) AS TIC " +
            "FROM institucion WHERE id_ciudad = :idMunicipio", nativeQuery = true)
    List<Object[]> findTotalProyectosMunicipio(Integer idMunicipio);

    @Query(value = "SELECT pa.id_proyecto AS id_proyecto, pa.nombre AS nombre_proyecto, p.nombre || ' ' || p.apellido AS nombre_docente, t.nombre AS tema, l.nombre AS nombre_competencia, " +
            "c.grado || ' ' || c.jornada as grado, pa.lecciones_aprendidas as lecciones, pa.fecha_inicio, pa.fecha_fin, pa.estado, pa.visibilidad, l.id_linea as idLinea, t.id_tema as idTema, " +
            "c.id as idGrado " +
            "FROM proyecto_aula pa " +
            "JOIN persona p ON p.cedula = pa.docente_lider " +
            "JOIN tema t ON t.id_tema = pa.id_tema " +
            "JOIN linea_transversal l ON l.id_linea = t.id_linea " +
            "JOIN curso c ON c.id = pa.grado " +
            "WHERE pa.id_proyecto = :idProyectoAula", nativeQuery = true)
    List<Object[]> findProyecto(Integer idProyectoAula);

    @Query(value = "SELECT pa.id_proyecto AS id_proyecto, pa.nombre AS nombre_proyecto, p.nombre || ' ' || p.apellido AS nombre_docente, t.nombre AS tema, l.nombre AS nombre_competencia, c.grado || ' ' || c.jornada as grado " +
            "FROM proyecto_aula pa " +
            "JOIN persona p ON p.cedula = pa.docente_lider " +
            "JOIN tema t ON t.id_tema = pa.id_tema " +
            "JOIN linea_transversal l ON l.id_linea = t.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "JOIN curso c ON c.id = pa.grado " +
            "WHERE i.id_institucion = :idInstitucion AND pa.visibilidad = 1 AND pa.estado = 'Aprobado'", nativeQuery = true)
    List<Object[]> findContenidosInstitucionPublico(Integer idInstitucion);

    @Query(value = "SELECT pa.id_proyecto AS id_proyecto, pa.nombre AS nombre_proyecto, p.nombre || ' ' || p.apellido AS nombre_docente, t.nombre AS tema, l.nombre AS nombre_competencia, c.grado || ' ' || c.jornada as grado " +
            "FROM proyecto_aula pa " +
            "JOIN persona p ON p.cedula = pa.docente_lider " +
            "JOIN tema t ON t.id_tema = pa.id_tema " +
            "JOIN linea_transversal l ON l.id_linea = t.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "JOIN curso c ON c.id = pa.grado " +
            "WHERE i.id_institucion = :idInstitucion AND EXTRACT(YEAR FROM pa.fecha_inicio) = :anio AND pa.visibilidad = 1 AND pa.estado = 'Aprobado'", nativeQuery = true)
    List<Object[]> findContenidosInstitucionPublicoFiltroAno(Integer idInstitucion, Integer anio);

    @Query(value = "SELECT pa.id_proyecto AS id_proyecto, pa.nombre AS nombre_proyecto, p.nombre || ' ' || p.apellido AS nombre_docente, t.nombre AS tema, l.nombre AS nombre_competencia, c.grado || ' ' || c.jornada as grado " +
            "FROM proyecto_aula pa " +
            "JOIN persona p ON p.cedula = pa.docente_lider " +
            "JOIN tema t ON t.id_tema = pa.id_tema " +
            "JOIN linea_transversal l ON l.id_linea = t.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "JOIN curso c ON c.id = pa.grado " +
            "WHERE i.id_institucion = :idInstitucion AND l.id_linea = :idLinea AND pa.visibilidad = 1 AND pa.estado = 'Aprobado'", nativeQuery = true)
    List<Object[]> findContenidosInstitucionPublicoFiltroLinea(Integer idInstitucion, Integer idLinea);

    @Query(value = "SELECT pa.id_proyecto AS id_proyecto, pa.nombre AS nombre_proyecto, p.nombre || ' ' || p.apellido AS nombre_docente, t.nombre AS tema, l.nombre AS nombre_competencia, c.grado || ' ' || c.jornada as grado " +
            "FROM proyecto_aula pa " +
            "JOIN persona p ON p.cedula = pa.docente_lider " +
            "JOIN tema t ON t.id_tema = pa.id_tema " +
            "JOIN linea_transversal l ON l.id_linea = t.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "JOIN curso c ON c.id = pa.grado " +
            "WHERE i.id_institucion = :idInstitucion AND l.id_linea = :idLinea AND EXTRACT(YEAR FROM pa.fecha_inicio) = :anio AND pa.visibilidad = 1 AND pa.estado = 'Aprobado'", nativeQuery = true)
    List<Object[]> findContenidosInstitucionPublicoFiltro(Integer idInstitucion, Integer idLinea, Integer anio);

    @Query(value = "SELECT pa.id_proyecto AS id_proyecto, pa.nombre AS nombre_proyecto, p.nombre || ' ' || p.apellido AS nombre_docente, t.nombre AS tema, l.nombre AS nombre_competencia, c.grado || ' ' || c.jornada as grado " +
            "FROM proyecto_aula pa " +
            "JOIN persona p ON p.cedula = pa.docente_lider " +
            "JOIN tema t ON t.id_tema = pa.id_tema " +
            "JOIN linea_transversal l ON l.id_linea = t.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "JOIN curso c ON c.id = pa.grado " +
            "WHERE pa.docente_lider = :idDocente ", nativeQuery = true)
    List<Object[]>listProyectosDocente(String idDocente);
}
