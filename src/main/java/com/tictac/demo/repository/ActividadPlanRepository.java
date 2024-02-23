package com.tictac.demo.repository;

import com.tictac.demo.entity.ActividadPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadPlanRepository extends JpaRepository<ActividadPlan, Integer> {

  List<ActividadPlan> findByIdPlan(Integer id);

  @Query(value = "SELECT pt.id_plan, pt.nombre as nombre_proyecto, pt.ano, pt.lecciones_aprendidas, lt.id_linea as linea, pt.estado as estado, pt.cierre as cierre, pt.recomendaciones as recomendaciones " +
          "FROM plan_trabajo pt " +
          "JOIN actividad_plan ap ON ap.id_plan = pt.id_plan " +
          "JOIN linea_transversal lt ON lt.id_linea = pt.id_linea " +
          "JOIN persona p ON p.cedula = ap.docente_apoyo " +
          "WHERE pt.id_plan = :idPlan", nativeQuery = true)
  List<Object[]> findProyectoAula(Integer idPlan);

  @Query(value = "SELECT ap.id_actividad, ap.nombre as nombre_actividad, ap.fecha_inicio, ap.fecha_fin, p.nombre || ' ' || p.apellido as nombre_docente, ap.cumplimiento, ap.observaciones, " +
          "p.cedula as cedula, ap.descripcion as descripcion " +
          "FROM plan_trabajo pt " +
          "JOIN actividad_plan ap ON ap.id_plan = pt.id_plan " +
          "JOIN linea_transversal lt ON lt.id_linea = pt.id_linea " +
          "JOIN persona p ON p.cedula = ap.docente_apoyo " +
          "WHERE pt.id_plan = :idPlan", nativeQuery = true)
  List<Object[]> getListActividadPlan(Integer idPlan);
}
