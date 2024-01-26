package com.tictac.demo.repository;

import com.tictac.demo.entity.Poblacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoblacionRepository extends JpaRepository<Poblacion, Integer> {
}
