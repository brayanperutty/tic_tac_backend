package com.tictac.demo.repository;

import com.tictac.demo.entity.ActividadProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadProyectoRepository extends JpaRepository<ActividadProyecto, Integer> {

    @Query(value = "SELECT ap.id_actividad, ap.nombre as nombre_actividad, ap.descripcion as descripcion, ap.cumplimiento as cumplimiento " +
            "FROM proyecto_aula pa " +
            "JOIN curso_proyecto cp ON cp.id_proyecto = pa.id_proyecto " +
            "JOIN actividad_proyecto ap ON ap.id_actividad = cp.id_actividad " +
            "WHERE pa.id_proyecto = :idProyectoAula", nativeQuery = true)
    List<Object[]> findActividadesProyecto(Integer idProyectoAula);


}
