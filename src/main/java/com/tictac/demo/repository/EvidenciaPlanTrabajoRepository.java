package com.tictac.demo.repository;

import com.tictac.demo.entity.EvidenciaPlanTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenciaPlanTrabajoRepository extends JpaRepository<EvidenciaPlanTrabajo, Integer> {
}
