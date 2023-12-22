package com.tictac.demo.repository;

import com.tictac.demo.entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {

    public Institucion findByNombre(String nombre);

    public List<Institucion> findByCiudad(Integer ciudad);
}
