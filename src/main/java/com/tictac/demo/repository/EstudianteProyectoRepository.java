package com.tictac.demo.repository;

import com.tictac.demo.entity.EstudianteProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteProyectoRepository extends JpaRepository<EstudianteProyecto, Integer> {

    List<EstudianteProyecto> findByIdProyecto(Integer idProyecto);

    boolean existsByIdProyectoAndIdEstudiante(Integer idProyecto, String idEstudiante);

    Optional<EstudianteProyecto> deleteByIdProyectoAndIdEstudiante(Integer idProyecto, String idEstudiante);
}