package com.tictac.demo.repository;

import com.tictac.demo.entity.LineaTransversal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaTransversalRepository extends JpaRepository<LineaTransversal, Integer> {
}
