package com.tictac.demo.service;

import com.tictac.demo.entity.Rol;
import com.tictac.demo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    public RolRepository rolRepository;

    public Optional<Rol> getRol(Integer id){
        return rolRepository.findById(id);
    }
    public Integer getRolByNombre(String nombre){
        Rol rol = rolRepository.findByNombre(nombre);
        return rol.getId_rol();
    }

    public Rol saveRol(Rol rol){
        return rolRepository.save(rol);
    }

    public void deleteRol(Integer id){
        rolRepository.deleteById(id);
    }

    public List<Rol> rolList(){
        return rolRepository.findAll();
    }
}
