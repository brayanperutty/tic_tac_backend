package com.tictac.demo.repository;

import com.tictac.demo.entity.ActividadProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadProyectoRepository extends JpaRepository<ActividadProyecto, Integer> {
}
