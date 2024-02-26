package com.tictac.demo.repository;

import com.tictac.demo.entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {

    Institucion findByNombre(String nombre);

    @Query(value = "SELECT i.nombre FROM institucion i WHERE i.id_institucion = :idInstitucion", nativeQuery = true)
    String findNombreInstitucion(Integer idInstitucion);

    @Query(value = "SELECT i.id_institucion, i.nombre as nombre_institucion, c.nombre as nombre_municipio, " +
            "(i.numero_herramientas_sociales + i.numero_herramientas_ambiental + i.numero_herramientas_emprendimiento +  i.numero_herramientas_sexualidad + i.numero_herramientas_tic) as herramientas_realizadas, " +
            "(i.numero_proyectos_sociales + i.numero_proyectos_ambiental + i.numero_proyectos_emprendimiento +  i.numero_proyectos_sexualidad + i.numero_proyectos_tic) as proyectos_realizados, " +
            "SUM(numero_herramientas_ambiental) AS ambiental, SUM(numero_herramientas_sexualidad) AS sexualidad, SUM(numero_herramientas_sociales) AS sociales, " +
            "SUM(numero_herramientas_emprendimiento) AS emprendimiento, SUM(numero_herramientas_tic) AS TIC " +
            "FROM institucion i " +
            "JOIN ciudad c ON c.id_ciudad = i.id_ciudad " +
            "WHERE i.id_ciudad = :idCiudad " +
            "GROUP BY i.id_institucion, i.nombre, nombre_institucion, nombre_municipio", nativeQuery = true)
    List<Object[]> findByIdCiudad(Integer idCiudad);

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY i.numero_proyectos_sociales + i.numero_proyectos_ambiental + i.numero_proyectos_emprendimiento +  i.numero_proyectos_sexualidad + i.numero_proyectos_tic DESC), " +
            "i.nombre as nombreInstitucion, " +
            "(i.numero_proyectos_sociales + i.numero_proyectos_ambiental + i.numero_proyectos_emprendimiento +  i.numero_proyectos_sexualidad + i.numero_proyectos_tic) as proyectosRealizados " +
            "FROM institucion i JOIN ciudad c ON i.id_ciudad = c.id_ciudad " +
            "WHERE i.id_ciudad = :idMunicipio LIMIT 3", nativeQuery = true)
    List<Object[]> findProyectosByMunicipio(Integer idMunicipio);

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY i.numero_proyectos_sociales + i.numero_proyectos_ambiental + i.numero_proyectos_emprendimiento +  i.numero_proyectos_sexualidad + i.numero_proyectos_tic DESC), " +
            "i.nombre as nombreInstitucion, c.nombre as municipioInstitucion, " +
            "(i.numero_proyectos_sociales + i.numero_proyectos_ambiental + i.numero_proyectos_emprendimiento +  i.numero_proyectos_sexualidad + i.numero_proyectos_tic) as proyectosRealizados " +
            "FROM institucion i JOIN ciudad c ON i.id_ciudad = c.id_ciudad LIMIT 3", nativeQuery = true)
    List<Object[]> findProyectosByDepartamento();

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY i.numero_herramientas_sociales + i.numero_herramientas_ambiental + i.numero_herramientas_emprendimiento +  i.numero_herramientas_sexualidad + i.numero_herramientas_tic DESC), " +
            "i.nombre as nombreInstitucion, " +
            "(i.numero_herramientas_sociales + i.numero_herramientas_ambiental + i.numero_herramientas_emprendimiento +  i.numero_herramientas_sexualidad + i.numero_herramientas_tic) as herramientasRealizadas " +
            "FROM institucion i JOIN ciudad c ON i.id_ciudad = c.id_ciudad " +
            "WHERE i.id_ciudad = :idMunicipio LIMIT 3", nativeQuery = true)
    List<Object[]> findHerramientasByMunicipio(Integer idMunicipio);

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY i.numero_herramientas_sociales + i.numero_herramientas_ambiental + i.numero_herramientas_emprendimiento +  i.numero_herramientas_sexualidad + i.numero_herramientas_tic DESC), " +
            "i.nombre as nombreInstitucion, c.nombre as municipioInstitucion, " +
            "(i.numero_herramientas_sociales + i.numero_herramientas_ambiental + i.numero_herramientas_emprendimiento +  i.numero_herramientas_sexualidad + i.numero_herramientas_tic) as herramientasRealizadas " +
            "FROM institucion i JOIN ciudad c ON i.id_ciudad = c.id_ciudad LIMIT 3", nativeQuery = true)
    List<Object[]> findHerramientasByDepartamento();

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, i.nombre as nombre_institucion, h.recurso as recurso, h.docente_autor as docenteAutor " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON p.id_poblacion = ph.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON h.docente_autor = pe.cedula " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE pe.id_institucion = :idInstitucion AND estado = 'Aprobado' AND visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, i.nombre, h.recurso, i.id_ciudad ", nativeQuery = true)
    List<Object[]> findHerramientasByInstitucion(Integer idInstitucion);

    @Query(value = "SELECT SUM(d.numero_contenidos_ambiental) AS ambiental, SUM(d.numero_contenidos_sexualidad) AS sexual, SUM(d.numero_contenidos_sociales) AS sociales, SUM(d.numero_contenidos_emprendimiento) AS emprendimiento, " +
            "SUM(d.numero_contenidos_tic) AS tic " +
            "FROM institucion i " +
            "JOIN persona p ON p.id_institucion = i.id_institucion " +
            "JOIN docente d ON d.id_docente = p.cedula " +
            "WHERE i.id_institucion = :id", nativeQuery = true)
    List<Object[]> findEstadisticasContenidos(Integer id);



}
