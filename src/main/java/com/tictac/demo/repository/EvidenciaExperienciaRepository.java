package com.tictac.demo.repository;

import com.tictac.demo.entity.EvidenciaExperiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvidenciaExperienciaRepository extends JpaRepository<EvidenciaExperiencia, Integer> {

    List<EvidenciaExperiencia> findByDocenteAutor(String idDocente);

}
