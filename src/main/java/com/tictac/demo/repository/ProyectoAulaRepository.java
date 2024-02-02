package com.tictac.demo.repository;

import com.tictac.demo.entity.ProyectoAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoAulaRepository extends JpaRepository<ProyectoAula, Integer> {

    @Query(value = "SELECT SUM(numero_proyectos_ambiental) AS ambiental, " +
            "SUM(numero_proyectos_sexualidad) AS sexualidad, " +
            "SUM(numero_proyectos_sociales) AS sociales, " +
            "SUM(numero_proyectos_emprendimiento) AS emprendimiento, " +
            "SUM(numero_proyectos_tic) AS TIC " +
            "FROM institucion", nativeQuery = true)
    List<Object[]> findTotalProyectosDeAula();
}
