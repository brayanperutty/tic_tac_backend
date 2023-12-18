package com.tictac.demo.repository;

import com.tictac.demo.entity.Momento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MomentoRepository extends JpaRepository<Momento, Integer> {
}
