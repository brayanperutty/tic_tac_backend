package com.tictac.demo.repository;

import com.tictac.demo.entity.DocentePlanTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocentePlanTrabajoRepository extends JpaRepository<DocentePlanTrabajo, String> {

    List<DocentePlanTrabajo> findByIdActividadPlan(Integer idActividadPlan);
}
