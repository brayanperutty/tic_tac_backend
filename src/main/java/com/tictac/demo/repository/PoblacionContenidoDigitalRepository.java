package com.tictac.demo.repository;

import com.tictac.demo.entity.PoblacionContenidoDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoblacionContenidoDigitalRepository extends JpaRepository<PoblacionContenidoDigital, Integer> {

    List<PoblacionContenidoDigital> findByIdContenidoDigital(Integer idContenidoDigital);

    boolean existsByIdContenidoDigitalAndIdPoblacion(Integer idContenidoDigital, Integer idPoblacion);

    Optional<PoblacionContenidoDigital> deleteByIdContenidoDigitalAndIdPoblacion(Integer idContenidoDigital, Integer idPoblacion);
}
