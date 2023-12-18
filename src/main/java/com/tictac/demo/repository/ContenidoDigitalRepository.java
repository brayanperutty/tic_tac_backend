package com.tictac.demo.repository;

import com.tictac.demo.entity.ContenidoDigital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoDigitalRepository extends JpaRepository<ContenidoDigital, Integer> {
}
