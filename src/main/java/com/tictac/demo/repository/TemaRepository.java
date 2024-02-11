package com.tictac.demo.repository;

import com.tictac.demo.entity.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Integer> {

    @Query(value = "SELECT t.id_tema, t.nombre FROM tema t WHERE t.id_linea = :idLinea", nativeQuery = true)
    List<Object[]> listTemaByLinea(Integer idLinea);
}
