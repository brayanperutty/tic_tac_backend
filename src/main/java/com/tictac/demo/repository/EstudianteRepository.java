package com.tictac.demo.repository;

import com.tictac.demo.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    @Query(value = "SELECT e.id_estudiante, e.nombre FROM estudiante e WHERE e.grado = :grado", nativeQuery = true)
    List<Object[]> listEstudiantesByCurso(Integer grado);
}
