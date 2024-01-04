package com.tictac.demo.repository;

import com.tictac.demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {

  List<Persona> findByIdInstitucion(Integer id);
}
