package com.tictac.demo.repository;

import com.tictac.demo.entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {

    Institucion findByNombre(String nombre);

    List<Institucion> findByIdCiudad(Integer idCiudad);

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY i.numero_proyectos_sociales + i.numero_proyectos_ambiental + i.numero_proyectos_emprendimiento +  i.numero_proyectos_sexualidad + i.numero_proyectos_tic DESC), " +
            "i.nombre as nombreInstitucion, " +
            "(i.numero_proyectos_sociales + i.numero_proyectos_ambiental + i.numero_proyectos_emprendimiento +  i.numero_proyectos_sexualidad + i.numero_proyectos_tic) as proyectosRealizados " +
            "FROM institucion i JOIN ciudad c ON i.id_ciudad = c.id_ciudad " +
            "WHERE i.id_ciudad = :idMunicipio", nativeQuery = true)
    List<Object[]> findProyectosByMunicipio(Integer idMunicipio);

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY i.numero_proyectos_sociales + i.numero_proyectos_ambiental + i.numero_proyectos_emprendimiento +  i.numero_proyectos_sexualidad + i.numero_proyectos_tic DESC), " +
            "i.nombre as nombreInstitucion, c.nombre as municipioInstitucion, " +
            "(i.numero_proyectos_sociales + i.numero_proyectos_ambiental + i.numero_proyectos_emprendimiento +  i.numero_proyectos_sexualidad + i.numero_proyectos_tic) as proyectosRealizados " +
            "FROM institucion i JOIN ciudad c ON i.id_ciudad = c.id_ciudad", nativeQuery = true)
    List<Object[]> findProyectosByDepartamento();

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY i.numero_herramientas_sociales + i.numero_herramientas_ambiental + i.numero_herramientas_emprendimiento +  i.numero_herramientas_sexualidad + i.numero_herramientas_tic DESC), " +
            "i.nombre as nombreInstitucion, " +
            "(i.numero_herramientas_sociales + i.numero_herramientas_ambiental + i.numero_herramientas_emprendimiento +  i.numero_herramientas_sexualidad + i.numero_herramientas_tic) as herramientasRealizadas " +
            "FROM institucion i JOIN ciudad c ON i.id_ciudad = c.id_ciudad " +
            "WHERE i.id_ciudad = :idMunicipio", nativeQuery = true)
    List<Object[]> findHerramientasByMunicipio(Integer idMunicipio);

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY i.numero_herramientas_sociales + i.numero_herramientas_ambiental + i.numero_herramientas_emprendimiento +  i.numero_herramientas_sexualidad + i.numero_herramientas_tic DESC), " +
            "i.nombre as nombreInstitucion, c.nombre as municipioInstitucion, " +
            "(i.numero_herramientas_sociales + i.numero_herramientas_ambiental + i.numero_herramientas_emprendimiento +  i.numero_herramientas_sexualidad + i.numero_herramientas_tic) as herramientasRealizadas " +
            "FROM institucion i JOIN ciudad c ON i.id_ciudad = c.id_ciudad", nativeQuery = true)
    List<Object[]> findHerramientasByDepartamento();



}
