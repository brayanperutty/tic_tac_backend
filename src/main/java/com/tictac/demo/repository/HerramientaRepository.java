package com.tictac.demo.repository;

import com.tictac.demo.entity.Herramienta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HerramientaRepository extends JpaRepository<Herramienta, Integer> {

    @Query(value="SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ') as nombrePoblacion, t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, " +
            "h.recomendacion, h.id_tema as tema, STRING_AGG(CAST(p.id_poblacion AS text), ', ') as idPoblacion, ltr.id_linea as lineaPPT, c.id_competencia as id_competencia, " +
            "h.visibilidad as visibilidad, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON h.id_tema = t.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "WHERE h.id_herramienta = :idHerramienta " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.recomendacion, h.id_tema, ltr.id_linea, c.id_competencia, h.visibilidad, h.uso ", nativeQuery = true)
    List<Object[]> findHerramientaById(Integer idHerramienta);

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY m.id_momento) as id_nuevo_momento, m.id_momento as id_momento, m.nombre, m.descripcion " +
            "FROM herramienta h JOIN momento m ON m.id_herramienta = h.id_herramienta " +
            "WHERE m.id_herramienta = :idHerramienta ORDER BY m.id_momento", nativeQuery = true)
    List<Object[]> findMomentosByHerramienta(Integer idHerramienta);

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY p.id_proceso) as id_nuevo_proceso, p.id_proceso as id_proceso, p.descripcion, p.tiempo " +
            "FROM proceso p JOIN momento m ON m.id_momento = p.id_momento " +
            "WHERE p.id_momento = :idMomento ORDER BY p.id_proceso ASC", nativeQuery = true)
    List<Object[]> findProcesosByMomento(Integer idMomento);

    @Query(value = "SELECT STRING_AGG(r.nombre, ', ' ORDER BY r.id_recurso) as recurso_nombre, r.id_recurso as recurso " +
            "FROM proceso p JOIN recurso_proceso rp ON rp.id_proceso = p.id_proceso " +
            "JOIN recurso r ON r.id_recurso = rp.id_recurso " +
            "WHERE p.id_proceso = :idProceso " +
            "GROUP BY r.id_recurso ", nativeQuery = true)
    List<Object[]> findRecursosByProceso(Integer idProceso);

    @Query(value="SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "WHERE h.estado = 'Aprobado' AND h.visibilidad = 1 GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.visibilidad, h.estado, h.uso ", nativeQuery = true)
    List<Object[]> findAllHerramientas();

    @Query(value = "SELECT SUM(numero_herramientas_ambiental) AS ambiental, " +
            "SUM(numero_herramientas_sexualidad) AS sexualidad, " +
            "SUM(numero_herramientas_sociales) AS sociales, " +
            "SUM(numero_herramientas_emprendimiento) AS emprendimiento, " +
            "SUM(numero_herramientas_tic) AS TIC " +
            "FROM institucion", nativeQuery = true)
    List<Object[]> findTotalHerramientas();

    @Query(value = "SELECT SUM(numero_herramientas_ambiental) AS ambiental, " +
            "SUM(numero_herramientas_sexualidad) AS sexualidad, " +
            "SUM(numero_herramientas_sociales) AS sociales, " +
            "SUM(numero_herramientas_emprendimiento) AS emprendimiento, " +
            "SUM(numero_herramientas_tic) AS TIC " +
            "FROM institucion WHERE id_ciudad = :idMunicipio", nativeQuery = true)
    List<Object[]> findTotalHerramientasMunicipio(Integer idMunicipio);

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON pe.cedula = h.docente_autor " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND ltr.id_linea = :idLinea AND EXTRACT(YEAR FROM h.fecha_aprobacion) = :anio AND h.estado = 'Aprobado' AND h.visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.visibilidad, h.estado, h.uso ", nativeQuery = true)
    List<Object[]> findHerramientasInstitucionPublicoFiltro(Integer idInstitucion, Integer idLinea, Integer anio);

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON pe.cedula = h.docente_autor " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND EXTRACT(YEAR FROM h.fecha_aprobacion) = :anio AND h.estado = 'Aprobado' AND h.visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.visibilidad, h.estado, h.uso", nativeQuery = true)
    List<Object[]> findHerramientasInstitucionPublicoFiltroAno(Integer idInstitucion, Integer anio);

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON pe.cedula = h.docente_autor " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND EXTRACT(YEAR FROM h.fecha_aprobacion) = :anio AND h.visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.visibilidad, h.estado, h.uso ", nativeQuery = true)
    List<Object[]> findHerramientasInstitucionPrivadoFiltroAno(Integer idInstitucion, Integer anio);

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON pe.cedula = h.docente_autor " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND ltr.id_linea = :idLinea AND h.estado = 'Aprobado' AND h.visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.visibilidad, h.estado, h.uso ", nativeQuery = true)
    List<Object[]> findHerramientasInstitucionPublicoFiltroLinea(Integer idInstitucion, Integer idLinea);

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON pe.cedula = h.docente_autor " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND ltr.id_linea = :idLinea AND h.visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.visibilidad, h.estado, h.uso", nativeQuery = true)
    List<Object[]> findHerramientasInstitucionPrivadoFiltroLinea(Integer idInstitucion, Integer idLinea);

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON pe.cedula = h.docente_autor " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND h.estado = :estado AND h.visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.visibilidad, h.estado", nativeQuery = true)
    List<Object[]> findHerramientasInstitucionPublicoFiltroEstado(Integer idInstitucion, String estado);

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON pe.cedula = h.docente_autor " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND EXTRACT(YEAR FROM h.fecha_aprobacion) = :anio AND h.estado = :estado AND h.visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.recurso, h.uso ", nativeQuery = true)
    List<Object[]> findHerramientasInstitucionPublicoFiltroEstadoAnio(Integer idInstitucion, String estado, Integer anio);

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON pe.cedula = h.docente_autor " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND ltr.id_linea = :idLinea AND h.estado = :estado AND h.visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.recurso, h.uso ", nativeQuery = true)
    List<Object[]> findHerramientasInstitucionPublicoFiltroEstadoLinea(Integer idInstitucion, String estado, Integer idLinea);

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON pe.cedula = h.docente_autor " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND ltr.id_linea = :idLinea AND EXTRACT(YEAR FROM h.fecha_aprobacion) = :anio AND h.visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.recurso, h.uso ", nativeQuery = true)
    List<Object[]> findHerramientasInstitucionPublicoFiltroAnioLinea(Integer idInstitucion, Integer idLinea, Integer anio);

    @Query(value = "SELECT h.id_herramienta, h.nombre_herramienta, STRING_AGG(p.nombre, ', ' ORDER BY p.id_poblacion) as poblacion, " +
            "t.nombre AS nombre_tema, h.objetivos, c.nombre AS nombre_competencia, h.recurso, h.uso as uso " +
            "FROM herramienta h " +
            "JOIN poblacion_herramienta ph ON h.id_herramienta = ph.id_herramienta " +
            "JOIN poblacion p ON ph.id_poblacion = p.id_poblacion " +
            "JOIN tema t ON t.id_tema = h.id_tema " +
            "JOIN linea_transversal ltr ON ltr.id_linea = t.id_linea " +
            "JOIN competencia c ON c.id_competencia = t.id_competencia " +
            "JOIN persona pe ON pe.cedula = h.docente_autor " +
            "JOIN institucion i ON i.id_institucion = pe.id_institucion " +
            "WHERE i.id_institucion = :idInstitucion AND ltr.id_linea = :idLinea AND EXTRACT(YEAR FROM h.fecha_aprobacion) = :anio AND h.estado = :estado AND h.visibilidad = 1 " +
            "GROUP BY h.id_herramienta, h.nombre_herramienta, t.nombre, h.objetivos, c.nombre, h.recurso, h.uso ", nativeQuery = true)
    List<Object[]> findHerramientasInstitucionPrivadoFiltro(Integer idInstitucion, Integer idLinea, Integer anio, String estado);

    @Query(value = "SELECT h.nombre_herramienta, h.uso " +
            "FROM herramienta h " +
            "ORDER BY h.uso DESC " +
            "LIMIT 3 ", nativeQuery = true)
    List<Object[]> rankingDepartamentoHerramientasByUso();
}
