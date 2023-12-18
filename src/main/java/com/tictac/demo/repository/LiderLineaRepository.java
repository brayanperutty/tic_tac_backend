package com.tictac.demo.repository;

import com.tictac.demo.entity.LiderLinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiderLineaRepository extends JpaRepository<LiderLinea, String> {
}
