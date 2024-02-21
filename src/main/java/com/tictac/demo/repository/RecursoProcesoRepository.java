package com.tictac.demo.repository;

import com.tictac.demo.entity.RecursoProceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecursoProcesoRepository extends JpaRepository<RecursoProceso, Integer> {

    void deleteByIdProcesoAndIdRecurso(Integer idProceso, Integer idRecurso);
}
