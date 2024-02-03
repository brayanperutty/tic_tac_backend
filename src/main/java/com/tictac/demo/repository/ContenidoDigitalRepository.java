package com.tictac.demo.repository;

import com.tictac.demo.entity.ContenidoDigital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContenidoDigitalRepository extends JpaRepository<ContenidoDigital, Integer> {

    @Query(value = "SELECT p.nombre || ' ' || p.apellido AS autor, cd.id_contenido_digital, cd.recomendacion, cd.fecha_aprobacion, cd.recurso FROM persona p " +
            "JOIN contenido_digital cd ON cd.docente_autor = p.cedula " +
            "JOIN linea_transversal lt ON lt.id_linea = cd.id_linea " +
            "WHERE cd.estado = 'Aprobado' AND cd.visibilidad = 1 " +
            "ORDER BY cd.id_contenido_digital ", nativeQuery = true)
    List<Object[]> findContenidosObservatorio();
}
