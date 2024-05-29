package com.tictac.demo.repository;

import com.tictac.demo.entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Integer> {


    @Query(value = "SELECT e.id as id, e.nombre as nombre_experiencia, e.descripcion, l.nombre as nombre_linea FROM experiencia e " +
            "JOIN linea_transversal l ON l.id_linea = e.id_linea", nativeQuery = true)
    List<Object[]> listExperiencia();

    @Query(value = "SELECT ev.recurso FROM experiencia e JOIN evidencia_experiencia ev ON ev.id_experiencia = e.id " +
            "WHERE ev.id_experiencia = :idExperiencia", nativeQuery = true)
    List<Object[]> listExperienciaEvidencias(Integer idExperiencia);
}
