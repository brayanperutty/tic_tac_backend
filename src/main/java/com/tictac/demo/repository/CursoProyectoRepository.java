package com.tictac.demo.repository;

import com.tictac.demo.entity.CursoProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoProyectoRepository extends JpaRepository<CursoProyecto, Integer> {
}
