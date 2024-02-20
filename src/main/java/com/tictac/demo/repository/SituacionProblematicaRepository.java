package com.tictac.demo.repository;

import com.tictac.demo.entity.SituacionProblematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacionProblematicaRepository extends JpaRepository<SituacionProblematica, Integer> {
    SituacionProblematica findByIdPlan(Integer idPlan);
}
