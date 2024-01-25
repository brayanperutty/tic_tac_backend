package com.tictac.demo.repository;

import com.tictac.demo.entity.EstudianteProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteProyectoRepository extends JpaRepository<EstudianteProyecto, String> {
}
