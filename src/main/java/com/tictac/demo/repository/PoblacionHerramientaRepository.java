package com.tictac.demo.repository;

import com.tictac.demo.entity.PoblacionHerramienta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoblacionHerramientaRepository extends JpaRepository<PoblacionHerramienta, Integer> {

    List<PoblacionHerramienta> findByIdHerramienta(Integer idHerramienta);

    Optional<PoblacionHerramienta> findByIdPoblacionAndIdHerramienta(Integer idPoblacion, Integer idHerramienta);
}
