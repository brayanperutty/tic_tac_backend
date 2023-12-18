package com.tictac.demo.repository;

import com.tictac.demo.entity.ActividadPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadPlanRepository extends JpaRepository<ActividadPlan, Integer> {
}
