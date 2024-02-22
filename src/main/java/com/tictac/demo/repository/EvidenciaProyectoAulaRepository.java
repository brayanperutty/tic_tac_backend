package com.tictac.demo.repository;

import com.tictac.demo.entity.EvidenciaProyectoAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenciaProyectoAulaRepository extends JpaRepository<EvidenciaProyectoAula, Integer> {
}
