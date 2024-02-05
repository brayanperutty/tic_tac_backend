package com.tictac.demo.repository;

import com.tictac.demo.entity.ContenidoDigital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContenidoDigitalRepository extends JpaRepository<ContenidoDigital, Integer> {

    @Query(value = "SELECT p.nombre || ' ' || p.apellido AS autor, cd.id_contenido_digital, cd.recomendacion, cd.fecha_aprobacion, cd.recurso FROM persona p " +
            "JOIN contenido_digital cd ON cd.docente_autor = p.cedula " +
            "JOIN linea_transversal lt ON lt.id_linea = cd.id_linea " +
            "WHERE cd.estado = 'Aprobado' AND cd.visibilidad = 1 " +
            "ORDER BY cd.id_contenido_digital ", nativeQuery = true)
    List<Object[]> findContenidosObservatorio();

    @Query(value = "SELECT p.nombre || ' ' || p.apellido AS autor, cd.id_contenido_digital, cd.recomendacion, cd.fecha_aprobacion, cd.recurso, cd.nombre_cont_digital AS nombre, lt.nombre as linea " +
            "FROM persona p " +
            "JOIN contenido_digital cd ON cd.docente_autor = p.cedula " +
            "JOIN linea_transversal lt ON lt.id_linea = cd.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND cd.estado = 'Aprobado' AND cd.visibilidad = 1 " +
            "ORDER BY cd.id_contenido_digital", nativeQuery = true)
    List<Object[]> findContenidosInstitucionPublico(Integer idInstitucion);


    @Query(value = "SELECT p.nombre || ' ' || p.apellido AS autor, cd.id_contenido_digital, cd.recomendacion, cd.fecha_aprobacion, cd.recurso, cd.nombre_cont_digital AS nombre, lt.nombre as linea " +
            "FROM persona p " +
            "JOIN contenido_digital cd ON cd.docente_autor = p.cedula " +
            "JOIN linea_transversal lt ON lt.id_linea = cd.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND lt.id_linea = :idLinea AND EXTRACT(YEAR FROM cd.fecha_aprobacion) = :anio AND cd.estado = 'Aprobado' AND cd.visibilidad = 1 " +
            "ORDER BY cd.id_contenido_digital", nativeQuery = true)
    List<Object[]> findContenidosInstitucionPublicoFiltro(Integer idInstitucion, Integer idLinea, Integer anio);

    @Query(value = "SELECT p.nombre || ' ' || p.apellido AS autor, cd.id_contenido_digital, cd.recomendacion, cd.fecha_aprobacion, cd.recurso, cd.nombre_cont_digital AS nombre, lt.nombre as linea " +
            "FROM persona p " +
            "JOIN contenido_digital cd ON cd.docente_autor = p.cedula " +
            "JOIN linea_transversal lt ON lt.id_linea = cd.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND EXTRACT(YEAR FROM cd.fecha_aprobacion) = :anio AND cd.estado = 'Aprobado' AND cd.visibilidad = 1 " +
            "ORDER BY cd.id_contenido_digital", nativeQuery = true)
    List<Object[]> findContenidosInstitucionPublicoFiltroAno(Integer idInstitucion, Integer anio);

    @Query(value = "SELECT p.nombre || ' ' || p.apellido AS autor, cd.id_contenido_digital, cd.recomendacion, cd.fecha_aprobacion, cd.recurso, cd.nombre_cont_digital AS nombre, lt.nombre as linea " +
            " " +
            "FROM persona p " +
            "JOIN contenido_digital cd ON cd.docente_autor = p.cedula " +
            "JOIN linea_transversal lt ON lt.id_linea = cd.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND lt.id_linea = :idLinea AND cd.estado = 'Aprobado' AND cd.visibilidad = 1 " +
            "ORDER BY cd.id_contenido_digital", nativeQuery = true)
    List<Object[]> findContenidosInstitucionPublicoFiltroLinea(Integer idInstitucion, Integer idLinea);

    @Query(value = "SELECT cd.nombre_cont_digital as nombre_cont, l.nombre as linea, p.nombre || ' ' || p.apellido as nombre_docente, cd.fecha_creacion as fecha, cd.recurso as recurso, cd.recomendacion as recomendacion " +
            "FROM contenido_digital cd " +
            "JOIN persona p ON p.cedula = cd.docente_autor " +
            "JOIN linea_transversal l ON l.id_linea = cd.id_linea " +
            "WHERE cd.id_contenido_digital = :idContenido", nativeQuery = true)
    List<Object[]> getContenidoDigital(Integer idContenido);

    @Query(value = "SELECT p.nombre || ' ' || p.apellido AS autor, cd.id_contenido_digital, cd.recomendacion, cd.fecha_aprobacion, cd.recurso, cd.nombre_cont_digital AS nombre " +
            "FROM persona p " +
            "JOIN contenido_digital cd ON cd.docente_autor = p.cedula " +
            "JOIN linea_transversal lt ON lt.id_linea = cd.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "WHERE lt.id_linea = :idLinea AND EXTRACT(YEAR FROM cd.fecha_aprobacion) = :anio AND cd.estado = 'Aprobado' AND cd.visibilidad = 1", nativeQuery = true)
    List<Object[]> getContenidosObservatorioFiltro(Integer idLinea, Integer anio);

    @Query(value = "SELECT p.nombre || ' ' || p.apellido AS autor, cd.id_contenido_digital, cd.recomendacion, cd.fecha_aprobacion, cd.recurso, cd.nombre_cont_digital AS nombre, lt.nombre as linea " +
            "FROM persona p " +
            "JOIN contenido_digital cd ON cd.docente_autor = p.cedula " +
            "JOIN linea_transversal lt ON lt.id_linea = cd.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "WHERE EXTRACT(YEAR FROM cd.fecha_aprobacion) = :anio AND cd.estado = 'Aprobado' AND cd.visibilidad = 1 " +
            "ORDER BY cd.id_contenido_digital", nativeQuery = true)
    List<Object[]> getContenidosObservatorioFiltroAno(Integer anio);

    @Query(value = "SELECT p.nombre || ' ' || p.apellido AS autor, cd.id_contenido_digital, cd.recomendacion, cd.fecha_aprobacion, cd.recurso, cd.nombre_cont_digital AS nombre, lt.nombre as linea " +
            " " +
            "FROM persona p " +
            "JOIN contenido_digital cd ON cd.docente_autor = p.cedula " +
            "JOIN linea_transversal lt ON lt.id_linea = cd.id_linea " +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "WHERE lt.id_linea = :idLinea AND cd.estado = 'Aprobado' AND cd.visibilidad = 1 " +
            "ORDER BY cd.id_contenido_digital", nativeQuery = true)
    List<Object[]> getContenidosObservatorioFiltroLinea(Integer idLinea);


}
