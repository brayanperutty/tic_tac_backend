package com.tictac.demo.repository;

import com.tictac.demo.entity.EstudianteProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteProyectoRepository extends JpaRepository<EstudianteProyecto, Integer> {

    List<EstudianteProyecto> findByIdActividad(Integer idActividad);

    boolean existsByIdActividadAndIdEstudiante(Integer idActividad, String idEstudiante);

    void deleteByIdActividadAndIdEstudiante(Integer idActividad, String idEstudiante);

    void deleteByIdActividad(Integer idActividad);
}
