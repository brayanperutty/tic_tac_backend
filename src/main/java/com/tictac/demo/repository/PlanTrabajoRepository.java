package com.tictac.demo.repository;

import com.tictac.demo.entity.PlanTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanTrabajoRepository extends JpaRepository<PlanTrabajo, Integer> {
}
