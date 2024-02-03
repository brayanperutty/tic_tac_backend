package com.tictac.demo.repository;

import com.tictac.demo.entity.Herramienta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HerramientaRepository extends JpaRepository<Herramienta, Integer> {

    @Query(value="SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON p.id_poblacion = ph.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "WHERE t.id_linea = :idLineaTransversal AND h.estado = 'Aprobado' AND h.visibilidad = 1 s" +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.visibilidad, h.estado", nativeQuery = true)
    List<Object[]> findHerramientaByLinea(Integer idLineaTransversal);

    @Query(value="SELECT h.id_herramienta, h.nombre_herramienta, p.nombre AS población, t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.visibilidad, h.estado, h.recomendacion " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON h.id_tema = t.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "WHERE h.id_herramienta = :idHerramienta", nativeQuery = true)
    List<Object[]> findHerramientaById(Integer idHerramienta);

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY m.id_momento) as id_nuevo_momento, m.id_momento as id_momento, m.nombre, m.descripcion " +
            "FROM herramienta h JOIN momento m ON m.id_herramienta = h.id_herramienta " +
            "WHERE m.id_herramienta = :idHerramienta ORDER BY m.id_momento", nativeQuery = true)
    List<Object[]> findMomentosByHerramienta(Integer idHerramienta);

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY p.id_proceso) as id_nuevo_proceso, p.id_proceso as id_proceso, p.descripcion, p.tiempo " +
            "FROM proceso p JOIN momento m ON m.id_momento = p.id_momento " +
            "WHERE p.id_momento = :idMomento ORDER BY p.id_proceso ASC", nativeQuery = true)
    List<Object[]> findProcesosByMomento(Integer idMomento);

    @Query(value = "SELECT STRING_AGG(r.nombre, ', ' ORDER BY r.id_recurso) as recurso_nombre " +
            "FROM proceso p JOIN recurso_proceso rp ON rp.id_proceso = p.id_proceso " +
            "JOIN recurso r ON r.id_recurso = rp.id_recurso " +
            "WHERE p.id_proceso = :idProceso GROUP BY p.id_proceso", nativeQuery = true)
    String findRecursosByProceso(Integer idProceso);

    @Query(value="SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "WHERE h.estado = 'Aprobado' AND h.visibilidad = 1 GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.visibilidad, h.estado", nativeQuery = true)
    List<Object[]> findAllHerramientas();

    @Query(value = "SELECT SUM(numero_herramientas_ambiental) AS ambiental, " +
            "SUM(numero_herramientas_sexualidad) AS sexualidad, " +
            "SUM(numero_herramientas_sociales) AS sociales, " +
            "SUM(numero_herramientas_emprendimiento) AS emprendimiento, " +
            "SUM(numero_herramientas_tic) AS TIC " +
            "FROM institucion", nativeQuery = true)
    List<Object[]> findTotalHerramientas();
}
