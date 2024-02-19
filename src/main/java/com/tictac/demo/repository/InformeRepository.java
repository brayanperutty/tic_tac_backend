package com.tictac.demo.repository;

import com.tictac.demo.entity.Informe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformeRepository extends JpaRepository<Informe, Integer> {


    @Query(value = "SELECT * FROM informe " +
                   "WHERE docente_autor = :idDocente ", nativeQuery = true)
    List<Informe> informesDocente(String idDocente);

    @Query(value = "SELECT i.id, i.nombre, i.fecha, i.recurso FROM informe i " +
                    "JOIN persona p ON p.cedula = i.docente_autor " +
                    "JOIN institucion ins ON ins.id_institucion = p.id_institucion " +
                    "WHERE ins.id_institucion = :idInstitucion ", nativeQuery = true)
    List<Informe> informesInstitucion(Integer idInstitucion);
}
