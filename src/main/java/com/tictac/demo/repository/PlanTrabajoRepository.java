package com.tictac.demo.repository;

import com.tictac.demo.entity.PlanTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanTrabajoRepository extends JpaRepository<PlanTrabajo, Integer> {

    @Query(value = "SELECT pt.id_plan, pt.nombre as nombre_plan, lt.nombre as nombre_linea, pt.ano as anio, pt.cierre as cierre " +
            "FROM plan_trabajo pt " +
            "JOIN actividad_plan ap ON ap.id_plan = pt.id_plan " +
            "JOIN linea_transversal lt ON lt.id_linea = pt.id_linea " +
            "JOIN persona p ON p.cedula = pt.docente_lider\n" +
            "JOIN institucion i ON i.id_institucion = p.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion", nativeQuery = true)
    List<Object[]> getListPlanTrabajo(Integer idInstitucion);


}
