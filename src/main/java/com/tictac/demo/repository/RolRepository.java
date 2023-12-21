package com.tictac.demo.repository;

import com.tictac.demo.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    public Rol findByNombre(String nombre);

}
