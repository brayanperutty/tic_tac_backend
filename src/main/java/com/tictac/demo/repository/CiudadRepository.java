package com.tictac.demo.repository;

import com.tictac.demo.entity.Ciudad;
import com.tictac.demo.entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
    
   Ciudad findByNombre(String nombre);

   @Query(value = "SELECT c.nombre FROM ciudad c WHERE c.id_ciudad = :idMunicipio", nativeQuery = true)
   String findNombreMunicipio(Integer idMunicipio);
}
