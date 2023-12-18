package com.tictac.demo.repository;

import com.tictac.demo.entity.ProyectoAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoAulaRepository extends JpaRepository<ProyectoAula, Integer> {
}
